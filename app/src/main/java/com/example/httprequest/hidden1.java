package com.example.httprequest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class hidden1 extends AppCompatActivity {
    String type = "Student";
    String field = "Field of study";
    static OkHttpClient client1 = new OkHttpClient();

    static OkHttpClient client2 = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden1);

        Animation animation = AnimationUtils.loadAnimation(hidden1.this, R.anim.bounce);


        LinearLayout studentSelected = findViewById(R.id.studentSelected);
        LinearLayout workerSelected = findViewById(R.id.workerSelected);

        TextInputLayout textInputLayout = findViewById(R.id.fieldOf);



        studentSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentSelected.startAnimation(animation);
                studentSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                workerSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                field = "Field of study";
                textInputLayout.setHint(field);
                type = "Student";

            }
        });

        workerSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerSelected.startAnimation(animation);
                workerSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                studentSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                field = "Field of occupation";
                textInputLayout.setHint(field);
                type = "Employed";
            }
        });

        EditText racee = findViewById(R.id.race);
        EditText religione = findViewById(R.id.religion);
        EditText fielde = findViewById(R.id.field);

        Button next = findViewById(R.id.hid1next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String race = Constants.capFirst(racee.getText().toString());
                String religion = Constants.capFirst(religione.getText().toString());
                String fieldd = Constants.capFirst(fielde.getText().toString());


                if (!validateInputs(race, religion, fieldd)) return;

//                                String[] nameParts = race.split(" ");
//
//                for (int i = 0; i < nameParts.length; i++) {
//                    nameParts[i] = Constants.capFirst(nameParts[i]);
//                }


                String[] values = {Constants.removeSpecialCharacters(race), Constants.removeSpecialCharacters(religion), type, Constants.escapeSpecialCharacters(fieldd),Constants.emaill, Constants.person_id};

                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("data", TextUtils.join(",", values));
                RequestBody requestBody = formBuilder.build();



                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/hiddens1.php")
                        .post(requestBody)
                        .build();


                client1.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Something went wrong
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            // Handle the successful response
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

//                                    Toaster.show(hidden1.this,);
                                    JSONObject jsonObject = null;
//                                    if (responseBody.equals("Success")) {
                                    try {
                                        jsonObject = new JSONObject(responseBody);
                                        Constants.child_id = jsonObject.getString("child_id");
//                                        Toaster.show(hidden1.this,Constants.child_id);

                                        RequestBody requestBody1 = new FormBody.Builder()
                                                .add("email",Constants.emaill)
                                                .add("child_id",Constants.child_id).
                                                build();

                                        Request request2 = new Request.Builder()
                                                .url("https://lamp.ms.wits.ac.za/home/s2610990/setChildId.php")
                                                .post(requestBody1)
                                                .build();

                                        client2.newCall(request2).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                System.out.println(e);
                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response responsee) throws IOException {
                                                if (responsee.isSuccessful()) {
                                                    runOnUiThread(new Runnable() {
                                                        String res = responsee.body().string();


                                                        @Override
                                                        public void run() {
                                                            if (res.contains("succ")){
                                                                Constants.race=race;
                                                                Constants.religion=religion;
                                                                Constants.typey=type;
                                                                Constants.field=fieldd;
//                                                                Toaster.show(hidden1.this,"INSERTED CHILD_ID");
                                                            }

//
//                                                            Toaster.show(Login.this,"mmmooppp");
                                                        }
                                                    });
                                                }else{
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toaster.show(hidden1.this,"Error oh no: " + responsee.code());
                                                        }
                                                    });
                                                }
                                            }
                                        });


                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }

                                        Toaster.show(hidden1.this,"Information added");
                                        Intent intent = new Intent(hidden1.this, hidden2.class);
                                        startActivity(intent);
                                        finish();
                                        // Do something else
//                                    } else {
//                                        Toaster.show(hidden1.this,"Something went wrong");
//                                        // Do something else
//                                    }
                                }
                            });
                        } else {
                            // Handle the error case
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toaster.show(hidden1.this,"Error: " + response.code());
                                }
                            });
                        }
                    }
                });
            }
        });




    }

    private boolean validateInputs(String username,String email,String password){

        if (username.isEmpty()){
            Toaster.show(this,"Race cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(username)){
//            Toaster.show(hidden1.this,"Race cannot contain any special characters");
//            return false;
//        }

        if (email.isEmpty()){
            Toaster.show(this,"Religion cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(email)){
//            Toaster.show(hidden1.this,"Religion cannot contain any special characters");
//            return false;
//        }

        if (password.isEmpty()){
            Toaster.show(this,field+" cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(username)){
//            Toaster.show(hidden1.this,field+" cannot contain any special characters");
//            return false;
//        }

        return true;
//        return false;
    }
}