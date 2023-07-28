package com.example.httprequest;

import static com.example.httprequest.SettingsActivity.isValidEmail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LinkEntity extends AppCompatActivity {

    static OkHttpClient client = new OkHttpClient();
    String entity_typee="Parent";

    RequestBody requestBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_entity);

        TextView title = findViewById(R.id.title);
        TextInputLayout link = findViewById(R.id.entityemail);
        ImageView back = findViewById(R.id.back);
        Button linkbtn = findViewById(R.id.linkbtn);


        if (Constants.typee.contains("Parent")){
            title.setText("Add your Child");
            linkbtn.setText("Link your Child");
        }else{
            title.setText("Add your Parent");
            linkbtn.setText("Link your Parent");
        }

        linkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String linkk = link.getText().toString();
                TextInputEditText linkEmailAddress = findViewById(R.id.linkEmailAddress);
                String entity_email = linkEmailAddress.getText().toString().replace(" ","");

                if (!validaInputs(entity_email)) return;

//                Toaster.show(LinkEntity.this,entity_email);



                if (Constants.typee.equals("Parent")){
                    entity_typee="Child";
                }else{
                    entity_typee="Parent";
                }

//                Toaster.show(LinkEntity.this,entity_email);
//                Toaster.show(LinkEntity.this,entity_typee);
//                Toaster.show(LinkEntity.this,Constants.emaill);
//                Toaster.show(LinkEntity.this,Constants.typee);

                if (Constants.typee.contains("Parents")){
                    requestBody = new FormBody.Builder()
                            .add("email",Constants.escapeSpecialCharacters(Constants.emaill))
                            .add("entity_email",Constants.escapeSpecialCharacters(entity_email))
                            .add("entity_typee",entity_typee)
                            .add("typee",Constants.typee)
                            .add("person_id",Constants.person_id)
                            .build();
                }else {
                    requestBody = new FormBody.Builder()
                            .add("email",Constants.emaill)
                            .add("entity_email",Constants.escapeSpecialCharacters(entity_email))
                            .add("entity_typee",entity_typee)
                            .add("typee",Constants.typee)
                            .add("person_id",Constants.person_id)
                            .build();
                }


                Request request2 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/addEntity1.php")
                        .post(requestBody)
                        .build();

                client.newCall(request2).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            String responseBodyy = response.body().string();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (responseBodyy.contains("Email does not exist")){
                                        Toaster.show(LinkEntity.this,"Email does not exist");
                                    }else if (responseBodyy.contains("Entity partner is already assigned for")){
                                        Toaster.show(LinkEntity.this,entity_email+" has already been assigned to a "+Constants.typee);
                                    }else if (responseBodyy.contains("Success")){
                                        Toaster.show(LinkEntity.this,entity_typee+" added successfully");
                                        Constants.isEntityPartnerNULL = false;
//                                        Constants.entity_partner=

                                        JSONObject jsonObject = null;

                                        try {
                                            jsonObject = new JSONObject(responseBodyy);

                                            Constants.entity_partner = jsonObject.getString("person_id");
                                            Constants.entityUsername = jsonObject.getString("username");
//                                            Toaster.show(LinkEntity.this,Constants.entityUsername);
                                            startActivity(new Intent(LinkEntity.this,MainActivity.class));
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                        System.out.println(responseBodyy);
//                                        Constants.entity_partner=


                                    }
                                }
                            });
                        }
                    }
                });


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LinkEntity.this,MainActivity.class));
            }
        });
    }

    private boolean validaInputs(String username) {

        if (username.isEmpty()){
            Toaster.show(this, "Email cannot be empty");
            return false;
        }

        if (!isValidEmail(username)){
            Toaster.show(this,"Email must be valid");
            return false;
        }

//        if (Constants.noSpecialChars(username)){
//            Toaster.show(LinkEntity.this,"Email cannot contain any special characters");
//            return false;
//        }

        return true;
    }
}