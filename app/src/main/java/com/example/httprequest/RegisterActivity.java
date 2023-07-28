package com.example.httprequest;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    static OkHttpClient client = new OkHttpClient();
    static OkHttpClient client2 = new OkHttpClient();
    static String responseStr;
    static String typee;

    static String username;
    static String email;
    static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        typee = "Parent";

        Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.bounce);

        LinearLayout parentSelected = findViewById(R.id.parentSelected);
        LinearLayout childSelected = findViewById(R.id.childSelected);

        Button btnRegister = findViewById(R.id.btnRegister);

        parentSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentSelected.startAnimation(animation);
                parentSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                childSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                typee="Parent";
                btnRegister.setText("Create account");
            }
        });

        childSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childSelected.startAnimation(animation);
                parentSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                childSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                typee="Child";
                btnRegister.setText("Create account");
            }
        });

        ImageView btnBack = findViewById(R.id.backreg);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        EditText Username = findViewById(R.id.Username);
        EditText Email = findViewById(R.id.Email);
        EditText Password = findViewById(R.id.Password);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String[] nameParts = etUsername.getText().toString().split(" ");
//
//                for (int i = 0; i < nameParts.length; i++) {
//                    nameParts[i] = Constants.capFirst(nameParts[i]);
//                }

                username = Constants.capFirst(Username.getText().toString()).trim();
                email = Email.getText().toString().replace(" ","");
                password = Password.getText().toString().replace(" ","");


                if (!Constants.validaInputs2(username, email, password,RegisterActivity.this)) return;

                username=Constants.escapeSpecialCharacters(username);
                email=Constants.escapeSpecialCharacters(email);
                password= Constants.escapeSpecialCharacters(Constants.encryptSHA256(password));

                String[] values = {username, email,password, typee, Integer.toString(R.drawable.user), "1"};
                Constants.firstTimeVerified=true;

                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("data", TextUtils.join(",", values));
                RequestBody requestBody = formBuilder.build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/insertToSignUp.php")
                        .post(requestBody)
                        .build();



                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Something went wrong
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (responseBody.equals("Success")) {
                                        Constants.username = Constants.unescapeSpecialCharacters(username);
                                        Constants.emaill =email;
                                        Constants.password = password;
                                        Constants.typee = typee;
                                        Constants.age="";
                                        Constants.aboutme="";
                                        Constants.phone="";
                                        // Assuming you have a drawable resource named "profile"
                                        int drawableResId = R.drawable.user;

                                        Log.d("Regg",drawableResId+"");

                                        Constants.profilePicture = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                                                getResources().getResourcePackageName(drawableResId) + '/' +
                                                getResources().getResourceTypeName(drawableResId) + '/' +
                                                getResources().getResourceEntryName(drawableResId));


                                        RequestBody requestBody1 = new FormBody.Builder()
                                                .add("email",Constants.emaill).build();

                                        Request request2 = new Request.Builder()
                                                .url("https://lamp.ms.wits.ac.za/home/s2610990/getPeronId.php")
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
                                                            JSONObject jsonObject = null;
                                                            System.out.println(res);
                                                            try {
                                                                jsonObject = new JSONObject(res);
                                                                System.out.println(jsonObject);
                                                                Constants.person_id = jsonObject.getString("person_id");
//                                                                Toaster.show(RegisterActivity.this,Constants.person_id);
//                                                                Toaster.show(hidden1.this,"PErons id is : "+Constants.person_id);

                                                                Toaster.show(RegisterActivity.this,"Registered successfully");

                                                                if (Constants.typee.equals("Parent")){

                                                                    Constants.isEntityPartnerNULL = true;
                                                                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                                                }else{
                                                                    startActivity(new Intent(RegisterActivity.this,hidden1.class));
                                                                }

                                                            } catch (JSONException e) {
                                                                throw new RuntimeException(e);
                                                            }



//                                                            Toaster.show(hidden1.this,"PErons id is : "+jsonObject.toString());
//                                                            Toaster.show(Login.this,"mmmooppp");
                                                        }
                                                    });
                                                }else{
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toaster.show(RegisterActivity.this,"Error oh no: " + responsee.code());
                                                        }
                                                    });
                                                }
                                            }
                                        });






                                    } else if (responseBody.equals("Email already exists")) {
                                        Toaster.show(RegisterActivity.this,"This email already exists");
                                    } else {
                                        Toaster.show(RegisterActivity.this,"Account was not created successfully");
                                    }
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toaster.show(RegisterActivity.this,"Error: " + response.code());
                                }
                            });
                        }
                    }
                });
            }
        });

    }

//    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//    Call post(String url, String json, Callback callback) {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//        return call;
//    }



}