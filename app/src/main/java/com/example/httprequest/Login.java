package com.example.httprequest;

import static com.example.httprequest.SettingsActivity.isValidEmail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {


    static OkHttpClient client = new OkHttpClient();
    static OkHttpClient client2 = new OkHttpClient();

    static OkHttpClient client3 = new OkHttpClient();
    static boolean bothPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnlgin = findViewById(R.id.lginbtn);
        EditText Email = findViewById(R.id.email);
        EditText Password = findViewById(R.id.pass);

        TextView notsigned = findViewById(R.id.tvsign);

        btnlgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                String email = Constants.capFirst(EmailAdd.getText().toString());
//                String password = Constants.capFirst(LogPassword.getText().toString());

                String email = Email.getText().toString().replace(" ", "");
                String password = Password.getText().toString().replace(" ", "");


                if (!Constants.validaInputs(email, password, Login.this)) return;

//                String[] values = {email, password};

                // Build the request body with the string array parameters

                RequestBody requestBody = new FormBody.Builder()
                        .add("email", Constants.escapeSpecialCharacters(email))
                        .add("password", Constants.escapeSpecialCharacters(Constants.encryptSHA256(password))).build();

                Request request2 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/login1.php")
                        .post(requestBody)
                        .build();

                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/getPass.php")
                        .post(requestBody)
                        .build();

//                if (Constants.isInternetAvailable()){
                client.newCall(request1).enqueue((new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBodyy = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (responseBodyy.contains("0")) {
                                        bothPass = false;
//                                        Toaster.show(Login.this, "Failed");
                                        client2.newCall(request2).enqueue(new Callback() {
                                            //                                            if ()
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response responsee) throws IOException {
                                                if (response.isSuccessful()) {
                                                    runOnUiThread(new Runnable() {
                                                        String res = responsee.body().string();

                                                        @Override
                                                        public void run() {
                                                            if (res.contains("smail")) {
                                                                Toaster.show(Login.this, "This email doesn't exist");
                                                            } else {
                                                                Toaster.show(Login.this, "Incorrect password");
                                                            }
//                                                        Toaster.show(Login.this,"mmmooppp");
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    } else if (responseBodyy.contains("1")) {
                                        bothPass = true;


//                                        Toaster.show(Login.this, "Passed");
                                        client2.newCall(request2).enqueue(new Callback() {
                                            //                                            if ()
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response responsee) throws IOException {
                                                if (responsee.isSuccessful())
                                                    runOnUiThread(new Runnable() {
                                                        String res = responsee.body().string();

                                                        @Override
                                                        public void run() {
                                                            JSONObject jsonObject = null;
                                                            try {

                                                                String[] parts = res.split("\\{");

//                                                                System.out.println();
//                                                                System.out.println();
//                                                                System.out.println(res);
//                                                                System.out.println();
//                                                                System.out.println();


                                                                jsonObject = new JSONObject("{"+parts[1]);

//                                                                jsonObject = new JSONObject(res);
                                                                Constants.username = Constants.unescapeSpecialCharacters(jsonObject.getString("username")).trim();
                                                                Constants.emaill = Constants.unescapeSpecialCharacters(jsonObject.getString("email"));
                                                                Constants.password = Constants.unescapeSpecialCharacters(jsonObject.getString("password"));
                                                                Constants.typee = jsonObject.getString("typee");
                                                                Constants.person_id = jsonObject.getString("person_id");

                                                                Constants.age = jsonObject.isNull("age") ? "" : Constants.unescapeSpecialCharacters(jsonObject.getString("age"));
                                                                Constants.phone = jsonObject.isNull("phoneNumber") ? "" : Constants.unescapeSpecialCharacters(jsonObject.getString("phoneNumber"));
                                                                Constants.aboutme = jsonObject.isNull("aboutMe") ? "" : Constants.unescapeSpecialCharacters(jsonObject.getString("aboutMe"));

                                                                if (Constants.typee.contains("Child")) {
                                                                    Constants.childId = jsonObject.getString("child_id");
                                                                    Constants.race = Constants.unescapeSpecialCharacters(jsonObject.getString("race"));
                                                                    Constants.religion = Constants.unescapeSpecialCharacters(jsonObject.getString("religion"));
                                                                    Constants.typey = jsonObject.getString("type");
                                                                    Constants.field = Constants.unescapeSpecialCharacters(jsonObject.getString("field"));
                                                                    Constants.gender = Constants.unescapeSpecialCharacters(jsonObject.getString("gender"));
                                                                    Constants.heightFt = Constants.unescapeSpecialCharacters(jsonObject.getString("heightft"));
                                                                    Constants.heightIn = Constants.unescapeSpecialCharacters(jsonObject.getString("heightin"));
                                                                    Constants.location = Constants.unescapeSpecialCharacters(jsonObject.getString("location"));
                                                                }

                                                                Constants.tmpphone = Constants.phone;
                                                                Constants.tmpage = Constants.age;
                                                                Constants.tmpabout = Constants.aboutme;

                                                                String firstTime = jsonObject.getString("firstTime");

                                                                if (firstTime.contains("1")) {
                                                                    Constants.firstTimeVerified = true;
                                                                } else {
                                                                    Constants.firstTimeVerified = false;
                                                                }

//                                            Toaster.show(Login.this,jsonObject.getString("profile_pic"));
//                                            String profilePicString = jsonObject.getString("profile_pic");
//                                            Constants.pfp = (profilePicString != null) ? Integer.parseInt(profilePicString) : 2131165389;

                                                                String profilePicString = jsonObject.getString("profile_pic");
                                                                Constants.pfp = (profilePicString != null && !profilePicString.equals("null")) ? Integer.parseInt(profilePicString) : R.drawable.user;

                                                                Constants.temppfp = Constants.pfp;

                                                                String entityPartner = jsonObject.isNull("entity_partner") ? null : jsonObject.getString("entity_partner");

                                                                if (entityPartner == null) {
                                                                    Constants.isEntityPartnerNULL = true;
                                                                } else {
                                                                    Constants.isEntityPartnerNULL = false;
                                                                    Constants.entity_partner = entityPartner;
                                                                    Constants.entityUsername = Constants.unescapeSpecialCharacters(jsonObject.getString("entity_partner_username")).trim();
//                                                Toaster.show(Login.this,Constants.entityUsername);
                                                                }

//                                            Constants.person_id = Integer.parseInt(jsonObject.getString("person_id"));
                                                                startActivity(new Intent(Login.this, MainActivity.class));
                                                                Toaster.show(Login.this, "Signed in successfully");
//                                            Toaster.show(Login.this,emailee);
//                                            Toaster.show(Login.this,typeeee);
//                                            Toaster.show(Login.this,passwordee);
                                                            } catch (JSONException e) {
                                                                throw new RuntimeException(e);
                                                            }
//                                                            Toaster.show(Login.this,"mmmooppp");
                                                        }
                                                    });
                                            }
                                        });

                                    }

//                                    Toaster.show(Login.this, Boolean.toString(bothPass));
                                }
                            });
                        }
                    }
                }));

//            }

//                client.newCall(request2).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        // Something went wrong
//                        System.out.println(e);
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        if (response.isSuccessful()) {
//                            String responseBody = response.body().string();
//                            String contentType = response.header("Content-Type");
//
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (responseBody.contains("smail")) {
////                                        System.out.println();
////                                        System.out.println(responseBody);
////                                        System.out.println();
//                                        Toaster.show(Login.this, "This email doesn't exist");
//
//                                    } else if (contentType.contains("application/json")) {
//                                        JSONObject jsonObject = null;
//                                        try {
//                                            jsonObject = new JSONObject(responseBody);
//                                            Constants.username = jsonObject.getString("username");
//                                            Constants.emaill = jsonObject.getString("email");
//                                            Constants.password = jsonObject.getString("password");
//                                            Constants.typee = jsonObject.getString("typee");
////                                            Constants.person_id = Integer.parseInt(jsonObject.getString("person_id"));
//                                            startActivity(new Intent(Login.this,MainActivity.class));
//                                            Toaster.show(Login.this,"Signed in successfully");
////                                            Toaster.show(Login.this,emailee);
////                                            Toaster.show(Login.this,typeeee);
////                                            Toaster.show(Login.this,passwordee);
//                                        } catch (JSONException e) {
//                                            throw new RuntimeException(e);
//                                        }
//
//                                    } else if (responseBody.contains("sword") ) {
////                                        System.out.println();
////                                        System.out.println(responseBody);
////                                        System.out.println();
//                                        Toaster.show(Login.this, "This password doesn't exist");
//                                    }
////                                    else {
////                                        if (!bothPass){
////                                            Toaster.show(Login.this, "Incorrect password");
////                                        }else {
////                                        processJSON(responseBody);
//
//
//
//                                        // Retrieve the values from the JSONObject
//
//                                        // Handle the response as a JSON array
////                                        Gson gson = new Gson();
////                                        ResponseData responseData = gson.fromJson(responseBody, ResponseData.class);
////
////// Access the fields in the response data
////                                        String uuusername = responseData.getUsername();
////                                        String uuemail = responseData.getEmail();
////                                        String uupassword = responseData.getPassword();
////                                        String uutypee = responseData.getTypee();
////
////                                        Toaster.show(Login.this,uuusername+uupassword+uuemail+uutypee);
////                                        String[] stringArray = new String[] { response.body() };
////
////                                        // Print the contents of the Java array
////                                        for (String str : stringArray) {
////                                            Toaster.show(Login.this,str);
////                                        }
//
////                                        Toaster.show(Login.this, "nothing");
////                                    }
//
//                                }
//                            });
//
//
//
//
//
//                        } else {
//
//                        }
//                    }
//                });

            }
        });

        notsigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(Login.this, R.anim.bounce);
                notsigned.startAnimation(animation);
                startActivity(new Intent(Login.this, RegisterActivity.class));
//                Constants.animateCuteButton(notsigned, new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animator) {
//                        // Animation start callback
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animator) {
//                        // Animation end callback
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animator) {
//                        // Animation cancel callback
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animator) {
//                        // Animation repeat callback
//                    }
//                });
            }
        });




    }


}

