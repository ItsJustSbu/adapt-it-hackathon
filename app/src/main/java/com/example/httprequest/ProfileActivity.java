package com.example.httprequest;

import static com.example.httprequest.Constants.pfp;
import static com.example.httprequest.Constants.temppfp;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileActivity extends AppCompatActivity {
    ImageView prflpic;
    static OkHttpClient client1 = new OkHttpClient();

    String[] values;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Toaster.show(ProfileActivity.this,temppfp+"tempti");



//        temppfp=pfp;

        if (Constants.firstTimeOnProfile){
//            temppfp= pfp;
            Constants.firstTimeOnProfile=false;
        }





        ImageView btnBack = findViewById(R.id.backprf);
        ImageView editbtn = findViewById(R.id.camera_image);

        EditText aget = findViewById(R.id.age);
        EditText phonet = findViewById(R.id.phone);
        EditText aboutt = findViewById(R.id.about);

        aget.setText(Constants.tmpage);

        phonet.setText(Constants.tmpphone);

        aboutt.setText(Constants.tmpabout);

        //

        Button submit = findViewById(R.id.submitchan);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String age = aget.getText().toString();
                String phone = phonet.getText().toString();
                String aboutme = aboutt.getText().toString();

//                Toaster.show(ProfileActivity.this,age);

                if (!validInput(age,phone,aboutme)) return;

//                Toaster.show(ProfileActivity.this, temppfp+"");

//                if (Constants.typee.contains("Parent")) {
//                    values = new String[]{Constants.removeSpecialCharacters(age), Constants.removeSpecialCharacters(phone), Constants.escapeSpecialCharacters(aboutme), Integer.toString(Constants.temppfp), Constants.escapeSpecialCharacters(Constants.emaill)};
//                }else {
//                    values = new String[]{Constants.removeSpecialCharacters(age), Constants.removeSpecialCharacters(phone), Constants.escapeSpecialCharacters(aboutme), Integer.toString(Constants.temppfp), Constants.emaill};
//                }

                values = new String[]{Constants.removeSpecialCharacters(age), Constants.removeSpecialCharacters(phone), Constants.escapeSpecialCharacters(aboutme.trim()), Integer.toString(Constants.temppfp), Constants.escapeSpecialCharacters(Constants.unescapeSpecialCharacters(Constants.emaill))};


                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("data", TextUtils.join(",", values));
                RequestBody requestBody = formBuilder.build();

                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/prof.php")
                        .post(requestBody)
                        .build();

                client1.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Something went wrong
                        System.out.println(e);
                    }
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(responseBody.contains("Success")){
                                    Constants.age=age;
                                    Constants.phone=phone;
                                    Constants.pfp=Constants.temppfp;
                                    Constants.aboutme=aboutme;
                                    Constants.tmpphone=Constants.phone;
                                    Constants.tmpage=Constants.age;
                                    Constants.tmpabout=Constants.aboutme;
                                    Constants.firstTimeOnProfile=true;

                                    Toaster.show(ProfileActivity.this,"Profile Updated");
                                    if (Constants.isEntityPartnerNULL){
                                        if (Constants.typee.contains("Parent")){
                                            Toaster.show(ProfileActivity.this,"Please link your Child to your account");
                                        }else {
                                            Toaster.show(ProfileActivity.this,"Please link your Parent to your account");
                                        }

                                    }

//                                    startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                                    }
                                }
                            });
                        }}});




            }
        });

        prflpic = findViewById(R.id.profile_image11);
//        prflpic.setImageResource(2131165379);
//        prflpic.setImageURI(Constants.profilePicture);

        if (Constants.temppfp== R.drawable.user){
            prflpic.setImageResource(R.drawable.user);
//            Toaster.show(ProfileActivity.this,R.drawable.user+"use6r");
//            Log.d("updatee",R.drawable.user+"");

        }else {
            prflpic.setImageResource(Constants.temppfp);
//            Toaster.show(ProfileActivity.this,Constants.temppfp+"temp");
//            System.out.println(temppfp+"");
        }

        Animation animation = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.bounce);

        prflpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.animateWobble(editbtn, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // Animation start callback
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        Intent iGallery = new Intent(Intent.ACTION_PICK);
//                        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(iGallery, GALLERY_REQUEST_CODE);
//                        requestStoragePermission();
//                        if (Constants.temppfp==-1){
//                            prflpic.setImageResource(R.drawable.user);
//                            Toaster.show(ProfileActivity.this,R.drawable.user+"user");
//                            Log.d("user",R.drawable.user+"");
//
//                        }else {
//                            prflpic.setImageResource(Constants.temppfp);
//                            Toaster.show(ProfileActivity.this,Constants.temppfp+"temp");
//
//                        }

                        String age = aget.getText().toString();
                        String phone = phonet.getText().toString();
                        String aboutme = aboutt.getText().toString();
                        Constants.tmpabout=aboutme;
                        Constants.tmpage=age;
                        Constants.tmpphone=phone;

//                        startActivity(new Intent(ProfileActivity.this, selectWallpaper.class));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        // Animation cancel callback
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        // Animation repeat callback
                    }
                });
            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                temppfp=pfp;
                Constants.tmpage =Constants.age;
                Constants.tmpabout = Constants.aboutme;
                Constants.tmpphone=Constants.phone;
                Constants.firstTimeOnProfile=true;
            }
        });

    }

    private boolean validInput(String age, String phone, String aboutme) {

        if (temppfp== R.drawable.user){
            Toaster.show(this,"Please select a picture");
            return false;
        }
        if (age.isEmpty()){
            Toaster.show(this,"Age cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(age)){
//            Toaster.show(ProfileActivity.this,"Age cannot contain any special characters");
//            return false;
//        }

        if (Integer.parseInt(age)<16){
            Toaster.show(this,"You are too young to use this app");
            return false;
        }

        if (phone.isEmpty()){
            Toaster.show(this,"Phone number cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(phone)){
//            Toaster.show(ProfileActivity.this,"Phone number cannot contain any special characters");
//            return false;
//        }

        if (phone.charAt(0)=='0' || phone.length()!=9){
            Toaster.show(this,"Please enter a valid phone number\n"+"example : 73 456 7890");
//            Toaster.show(this,);
            return false;
        }

        if (aboutme.isEmpty()){
            Toaster.show(this,"About me cannot be empty");
            return false;
        }

//        if (Constants.noSpecialChars(aboutme)){
//            Toaster.show(ProfileActivity.this,"About me cannot contain any special characters");
//            return false;
//        }

        if (aboutme.length()<300){
            Toaster.show(this,"About me has too little characters\n"+aboutme.length()+"/"+300);
//            Toaster.show(this,aboutme.length()+"/"+500);
            return false;
        }

        if (aboutme.length()>550){
            Toaster.show(this,"About me has too many characters\n"+aboutme.length()+"/"+550);
//            Toaster.show(this,aboutme.length()+"/"+500);
            return false;
        }

        return true;
    }
}