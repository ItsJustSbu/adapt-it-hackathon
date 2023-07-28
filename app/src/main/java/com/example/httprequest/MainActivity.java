package com.example.httprequest;

import static com.example.httprequest.Constants.pfp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.PropertyValuesHolder;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.smarteist.autoimageslider.SliderView;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    OkHttpClient client56 = new OkHttpClient();



    static OkHttpClient client3 = new OkHttpClient();

    String out = "";

    RequestBody requestBody69;


    String cc ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toaster.show(MainActivity.this,Constants.isEverythingCompleted+"completed");
//        Toaster.show(MainActivity.this,Constants.isEntityPartnerNULL+"null");
//        Toaster.show(MainActivity.this,Constants.isEverythingCompleted+"");
//        String conditionString = "";
//        conditionString += "isEverythingCompleted: " + Constants.isEverythingCompleted + "\n";
//        conditionString += "isEntityPartnerNULL: " + Constants.isEntityPartnerNULL + "\n";
//        conditionString += "age is empty: " + Constants.age.isEmpty() + "\n";
//        conditionString += "phone is empty: " + Constants.phone.isEmpty() + "\n";
//        conditionString += "aboutme is empty: " + Constants.aboutme.isEmpty() + "\n";
//        conditionString += "pfp is not default: " + (pfp != 2131165389);
//
//        Toaster.show(MainActivity.this,conditionString);

//        String conditionString = "";
//
//        conditionString += "child_id is empty: " + Constants.child_id.isEmpty() + "\n";
//        conditionString += "race is empty: " + Constants.race.isEmpty() + "\n";
//        conditionString += "religion is empty: " + Constants.religion.isEmpty() + "\n";
//        conditionString += "typey is empty: " + Constants.typey.isEmpty() + "\n";
//        conditionString += "field is empty: " + Constants.field.isEmpty() + "\n";
//        conditionString += "gender is empty: " + Constants.gender.isEmpty() + "\n";
//        conditionString += "heightFt is empty: " + Constants.heightFt.isEmpty() + "\n";
//        conditionString += "heightIn is empty: " + Constants.heightIn.isEmpty() + "\n";
//        conditionString += "location is empty: " + Constants.location.isEmpty();
//
//        Toaster.show(MainActivity.this, conditionString);




        if (!Constants.isEverythingCompleted && !Constants.isEntityPartnerNULL && !Constants.age.isEmpty() && !Constants.phone.isEmpty() && !Constants.aboutme.isEmpty() && pfp!=R.drawable.user){
            if (Constants.typee.contains("Parent")){
                Constants.isEverythingCompleted=true;
            }else{
                if (!Constants.child_id.isEmpty() && !Constants.race.isEmpty() && !Constants.religion.isEmpty() && !Constants.typey.isEmpty() && !Constants.field.isEmpty() && !Constants.gender.isEmpty() && !Constants.heightFt.isEmpty() && !Constants.heightIn.isEmpty() && !Constants.location.isEmpty()){
                    Constants.isEverythingCompleted=true;
                }

            }
        }


        LinearLayout aboutmeDone = findViewById(R.id.aboutmedone);
        LinearLayout profilebtn = findViewById(R.id.editprf);
        LinearLayout famornews = findViewById(R.id.news);

        ImageView profile_image = findViewById(R.id.profile_image);

        if (Constants.pfp== R.drawable.user){
            profile_image.setImageResource(R.drawable.user);

        }else {
            profile_image.setImageResource(pfp);
//            Toaster.show(MainActivity.this,pfp+"");

        }

//        LinearLayout fam = findViewById(R.id.fam);
        LinearLayout profilep2 = findViewById(R.id.editpfrlp);


        profilep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                profilep2.startAnimation(animation);
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });

        LinearLayout famornews1 = findViewById(R.id.famOrnews);



        famornews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                famornews1.startAnimation(animation);
                if (Constants.isEverythingCompleted){

//                    if (!Constants.newsVisted){

                    if (Constants.typee.equals("Parent")){
                        requestBody69 = new FormBody.Builder()
                                .add("entity_id", Constants.entity_partner)
                                .build();
                    }else{
                        requestBody69 = new FormBody.Builder()
                                .add("entity_id", Constants.person_id)
                                .build();
                    }




                    Request request1 = new Request.Builder()
                            .url("https://lamp.ms.wits.ac.za/home/s2610990/getAllMatches3.php")
                            .post(requestBody69)
                            .build();

                    client56.newCall(request1).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            System.out.println(e);
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String d =response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            // Parse the JSON response
                                            JSONArray jsonArray = new JSONArray(d);
                                            Constants.matchesItems.clear();
                                            Constants.matchespics.clear();

                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                                System.out.println(jsonObject);


                                                Constants.matchesItems.add(new matchInfo(
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("username")),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("email")),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("age")),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("phoneNumber")),
                                                        jsonObject.getString("profile_pic"),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("aboutMe")),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("location")),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("field")),
                                                        Constants.unescapeSpecialCharacters(jsonObject.getString("race"))
                                                ));

                                                Constants.matchespics.add(new newsitem(Integer.parseInt(jsonObject.getString("profile_pic"))));


                                            }

//                                            Constants.newsVisted=true;

                                            if (Constants.matchesItems.size()==0){
                                                if (Constants.typee.equals("Parent")){
                                                    Toaster.show(MainActivity.this,Constants.entityUsername+" has no matches yet");

                                                }else{
                                                    Toaster.show(MainActivity.this,"You have no matches yet");

                                                }
                                            }
                                            else{
                                                Constants.mat=Constants.matchespics.size()-1;
                                                if (Constants.typee.equals("Parent")){
                                                    Toaster.show(MainActivity.this,Constants.unescapeSpecialCharacters(Constants.matchesItems.get(Constants.mat).getUsername()).trim()+" has matched with "+Constants.unescapeSpecialCharacters(Constants.entityUsername).trim());


                                                }else{
                                                    Toaster.show(MainActivity.this,Constants.unescapeSpecialCharacters(Constants.matchesItems.get(Constants.mat).getUsername()).trim()+" has matched with you");

                                                }
//                                                Toaster.show(MainActivity.this,Constants.matchesItems.get(Constants.mat).getUsername()+" has matched with");
                                                startActivity(new Intent(MainActivity.this,news.class));
                                            }


//                                            startActivity(new Intent(MainActivity.this,beforeNews.class));

//                                Toaster.show(Swipes.this,"Completed");

                                            // Now you can use the ArrayLists containing the extracted data as needed

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



//                            textView.setText(d);
                                    }
                                });
                            }
                        }
                    });
//                    }
//                else{
//
//                    startActivity(new Intent(MainActivity.this,beforeNews.class));
//
//                }
                }else{
                    if (Constants.isEntityPartnerNULL){
                        startActivity(new Intent(MainActivity.this,LinkEntity.class));
                    }
                    else {
                        if (Constants.typee.contains("Parent")){
                            Toaster.show(MainActivity.this,"Your child has been linked to your account" +
                                                                            "Please complete your profile");
                        }else{
                            Toaster.show(MainActivity.this,"Your parent has been linked to your account" +
                                                                             "Please complete your profile");

                        }

                    }
                    }
            }
        });


        ImageView settingsImage = findViewById(R.id.setimage);

        TextView name = findViewById(R.id.accName1);
        TextView typee = findViewById(R.id.relationship1);

        ImageView logoutImage = findViewById(R.id.logimage);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(settingsImage, "rotation", 0f, 360f);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(0); // Set to 0 for one rotation



        LinearLayout settingsbtn = findViewById(R.id.set);
        settingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAnimator.start();
                rotateAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // Animation start callback
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
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

        LinearLayout btnlogout = findViewById(R.id.logoutb);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.age = "";
                Constants.phone = "";
                Constants.aboutme = "";

                Constants.pfpindexx = -1;
                Constants.isFirstTimeOnSEttings = true;

                Constants.username = "huzii";

                Constants.child_id = "70";
                Constants.person_id = "11"; //was 10
                Constants.isEntityPartnerNULL = false;
                Constants.emaill = "huziibee@gmail.com";
                Constants.password = "password";
//                Constants.typee = "Parent";

                Constants.firstTimeOnProfile = true;

                Constants.firstTimeVerified = true;

                Constants.isEverythingCompleted = false;
                Constants.matchespics.clear();
                Constants.matchesItems.clear();
                Constants.childId = "";
                Constants.race = "";
                Constants.religion = "";
                Constants.typey = "";
                Constants.field = "";
                Constants.gender = "";
                Constants.heightFt = "";
                Constants.heightIn = "";
                Constants.location = "";
                Constants.entityUsername ="";

                Constants.tmpage = "";
                Constants.tmpabout = "";
                Constants.tmpphone = "";

                Constants.entity_partner = "";
                Constants.temppfp = R.drawable.user;


                Constants.pfp= R.drawable.user;
                Toaster.show(MainActivity.this, "You have been signed out");

                startActivity(new Intent(MainActivity.this, Login.class));

            }
        });

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);


        ImageView verified = findViewById(R.id.verii);


        verified.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                verified.startAnimation(animation);
                if(Constants.isEverythingCompleted){
                    Toaster.show(MainActivity.this,"You are a Verified account user");

                }else {
                    Toaster.show(MainActivity.this,"You are not a Verified account");
                }
            }
        });



        famornews.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                famornews.startAnimation(animation);

                if (Constants.typee.equals("Parent")){
                    requestBody69 = new FormBody.Builder()
                            .add("entity_id", Constants.entity_partner)
                            .build();
                }else{
                    requestBody69 = new FormBody.Builder()
                            .add("entity_id", Constants.person_id)
                            .build();
                }




                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/getAllMatches3.php")
                        .post(requestBody69)
                        .build();

                client56.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String d =response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        // Parse the JSON response
                                        JSONArray jsonArray = new JSONArray(d);

                                        // Iterate over each JSON object in the array
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                                            Constants.matchesItems.clear();
                                            Constants.matchespics.clear();


                                            Constants.matchesItems.add(new matchInfo(
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("username")),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("email")),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("age")),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("phoneNumber")),
                                                    jsonObject.getString("profile_pic"),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("aboutMe")),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("location")),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("field")),
                                                    Constants.unescapeSpecialCharacters(jsonObject.getString("race"))
                                            ));

                                            Constants.matchespics.add(new newsitem(Integer.parseInt(jsonObject.getString("profile_pic"))));

//                                            Log.d("progress",jsonObject.toString());
//
//                                            Log.d("progress",Constants.matchespics.size()+"");


                                        }

                                        if (Constants.matchesItems.size()==0){
                                            if (Constants.typee.equals("Parent")){
                                                Toaster.show(MainActivity.this,Constants.unescapeSpecialCharacters(Constants.entityUsername+" has no matches yet"));

                                            }else{
                                                Toaster.show(MainActivity.this,"You have no matches yet");

                                            }
                                        }
                                        else{
                                            Constants.mat=Constants.matchespics.size()-1;
                                            if (Constants.typee.equals("Parent")){
                                                Toaster.show(MainActivity.this,Constants.unescapeSpecialCharacters(Constants.matchesItems.get(Constants.mat).getUsername()).trim()+" has matched with "+Constants.unescapeSpecialCharacters(Constants.entityUsername).trim());


                                            }else{
                                                Toaster.show(MainActivity.this,Constants.unescapeSpecialCharacters(Constants.matchesItems.get(Constants.mat).getUsername()).trim()+" has matched with you");

                                            }
//                                            Toaster.show(MainActivity.this,Constants.matchesItems.get(Constants.mat).getUsername()+" has matched with");
                                            startActivity(new Intent(MainActivity.this,news.class));
                                        }

//                                Toaster.show(Swipes.this,"Completed");


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }



//                            textView.setText(d);
                                }
                            });
                        }
                    }
                });
            }
        });

//        Toaster.show(MainActivity.this,Constants.firstTimeVerified+""+Constants.isEverythingCompleted);

        if (Constants.firstTimeVerified && Constants.isEverythingCompleted) {
            // ... your existing code ...

            Toaster.show(MainActivity.this,"Congratulations!! You are now verified");
            if (Constants.typee.contains("Parent")) {
                Toaster.show(MainActivity.this, "New pages are now accessible");
            }else {
                Toaster.show(MainActivity.this, "News page is now accessible");
            }

//            String[] values = {Constants.person_id, "0"};

            FormBody.Builder formBuilder2 = new FormBody.Builder();
            formBuilder2.add("person_id", Constants.person_id);
            formBuilder2.add("firstTime", "0");
            RequestBody requestBody3 = formBuilder2.build();

            // Build the request
            Request request3 = new Request.Builder()
                    .url("https://lamp.ms.wits.ac.za/home/s2610990/noLongerFirstTime.php")
                    .post(requestBody3)
                    .build();

            client3.newCall(request3).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    System.out.println(e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response respons) throws IOException {
                    if (respons.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            String res33 = respons.body().string();

                            @Override
                            public void run() {
                                if (res33.contains("Success")) {
//                                    Toaster.show(MainActivity.this, "Updated");
                                    Constants.firstTimeVerified= false;
                                }
                            }
                        });
                    }
                }
            });
            Constants.firstTimeVerified = false;
        }


        RelativeLayout part1  = findViewById(R.id.part1);
        RelativeLayout part2  = findViewById(R.id.part2);

        ImageView kisincomp = findViewById(R.id.kidincom);
        ImageView kiscomp = findViewById(R.id.kidcomp);


        if(Constants.isEverythingCompleted){
            verified.setImageResource(R.drawable.verified);
            if (Constants.typee.contains("Parent")){
                part1.setVisibility(View.GONE);
                part2.setVisibility(View.VISIBLE);
            }else{
                part1.setVisibility(View.VISIBLE);
                part2.setVisibility(View.GONE);
                kisincomp.setVisibility(View.GONE);
                kiscomp.setVisibility(View.VISIBLE);
            }


        }else {
            verified.setImageResource(R.drawable.notverified);
            part1.setVisibility(View.VISIBLE);
            part2.setVisibility(View.GONE);
            kisincomp.setVisibility(View.VISIBLE);
            kiscomp.setVisibility(View.GONE);


        }


        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                profilebtn.startAnimation(animation);
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        LinearLayout tinder = findViewById(R.id.tinder);
        tinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                tinder.startAnimation(animation);
                startActivity(new Intent(MainActivity.this, Swipes.class));
            }
        });

        name.setText(Constants.username);
        typee.setText(Constants.typee);

        SliderView sliderView = findViewById(R.id.sldview);

        List<items> itemss = new ArrayList<>();
        if (!Constants.isEverythingCompleted){
            itemss.add(new items(R.drawable.marriage, "United Hearts, Honored Traditions", "Welcome new user to PAM"));
            if (Constants.isEntityPartnerNULL){
            if (Constants.typee.contains("Parent")){
                itemss.add(new items(R.drawable.circle, "Link your child to your account", "Include your child for seamless organization"));
            }else{

                itemss.add(new items(R.drawable.circle, "Link your parent to your account", "Include your parent for seamless organization"));
            }}
            if (Constants.age.isEmpty()) {
                itemss.add(new items(R.drawable.proff, "Complete your profile", "Secure your account and unlock benefits"));
            }
        }else{
            if (Constants.typee.contains("Parent")) {
                cc = "New pages are now accessible";
                itemss.add(new items(R.drawable.verified, "You are a Verified account user", cc));
                itemss.add(new items(R.drawable.circle, "Your child has been linked", "Username: "+Constants.unescapeSpecialCharacters(Constants.entityUsername)));
            }else {
                cc ="News page is now accessible";
                itemss.add(new items(R.drawable.verified, "You are a Verified account user", cc));
                itemss.add(new items(R.drawable.circle, "Your parent has been linked", "Username: "+Constants.unescapeSpecialCharacters(Constants.entityUsername)));
            }

        }

        SliderAdapter sliderAdapter = new SliderAdapter(MainActivity.this, itemss);
        sliderView.setSliderAdapter(sliderAdapter);


    }




//        return responseStr;









}