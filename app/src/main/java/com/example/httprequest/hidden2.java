package com.example.httprequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class hidden2 extends AppCompatActivity {
    String gender = "Female";


    static OkHttpClient client1 = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden2);
        Animation animation = AnimationUtils.loadAnimation(hidden2.this, R.anim.bounce);

        LinearLayout femaleSelected = findViewById(R.id.femaleSelected);
        LinearLayout maleSelected = findViewById(R.id.maleSelected);

        femaleSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleSelected.startAnimation(animation);
                femaleSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                maleSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                gender = "Female";

            }
        });

        maleSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleSelected.startAnimation(animation);
                maleSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                femaleSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                gender = "Male";

            }
        });


        EditText heightfte = findViewById(R.id.Heightft);
        EditText heightine = findViewById(R.id.Heightin);
        EditText locatione = findViewById(R.id.location);

        Button createAcc = findViewById(R.id.createaccbtn);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heightft = Constants.capFirst(heightfte.getText().toString());
                String heightin = Constants.capFirst(heightine.getText().toString());
                String location = Constants.capFirst(locatione.getText().toString());

                if (!validateInputs(heightft,heightin,location)) return;

                String[] values = {gender,Constants.removeSpecialCharacters(heightft),Constants.removeSpecialCharacters(heightin),Constants.escapeSpecialCharacters(location), Constants.child_id};

                RequestBody requestBody = new FormBody.Builder()
                        .add("child_id", Constants.child_id)
                        .add("gender", gender)
                        .add("heightft", heightft)
                        .add("heightin", heightin)
                        .add("location", location)
                        .build();

                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/hiddens2.php")
                        .post(requestBody)
                        .build();

                client1.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toaster.show(hidden2.this,"Account created successfully");
                                    Constants.isEntityPartnerNULL = true;
                                    Constants.gender=gender;
                                    Constants.heightFt=heightft;
                                    Constants.heightIn=heightin;
                                    Constants.location=location;
                                    Intent intent = new Intent(hidden2.this, ProfileActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        } else {
                            // Handle the error case
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toaster.show(hidden2.this,"Error: " + response.code());
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
            Toaster.show(this,"Height (ft.) cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(username)){
//            Toaster.show(hidden2.this,"Height (ft.) cannot contain any special characters");
//            return false;
//        }

        if (email.isEmpty()){
            Toaster.show(this,"Height (in.) cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(email)){
//            Toaster.show(hidden2.this,"Height (in.) cannot contain any special characters");
//            return false;
//        }

        if (password.isEmpty()){
            Toaster.show(this,"Location cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(password)){
//            Toaster.show(hidden2.this,"Location cannot contain any special characters");
//            return false;
//        }

        return true;
//        return false;
    }
}