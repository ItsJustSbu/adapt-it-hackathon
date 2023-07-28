package com.example.httprequest;

import static com.example.httprequest.Child.usernames;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Swipes extends AppCompatActivity {

    private ActionBar actionBar;
    private ViewPager viewPager;

    static OkHttpClient client = new OkHttpClient();
    private ArrayList<ChildInfo> modelArrayList = new ArrayList<>();
    private SwipeAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipes);
//        Child child = new Child();

        ImageView btnBack = findViewById(R.id.backswi);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Swipes.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        viewPager = findViewById(R.id.viewPager);

//        Toaster.show(Swipes.this,Constants.entity_partner);
//
//        RequestBody r =new FormBody.Builder().add("entity_id", Constants.entity_partner).build();
//
//        Request request1 = new Request.Builder()
//                .url("https://lamp.ms.wits.ac.za/home/s2610990/check.php")
//                .post(r)
//                .build();
//
//        client.newCall(request1).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                System.out.println(e);
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.isSuccessful()){
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Toaster.show(Swipes.this,response.body().string());
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    });
//                }
//            }
//        });

        RequestBody requestBody = new FormBody.Builder()
                .add("entity_id", Constants.entity_partner)
                .build();

        Request request1 = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2610990/getAllKids1.php")
                .post(requestBody)
                .build();

        client.newCall(request1).enqueue(new Callback() {
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
                                JSONArray jsonArray = new JSONArray(d);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                                    modelArrayList.add(new ChildInfo(
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("username")).trim(),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("age")).trim(),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("race")).trim(),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("religion")).trim(),
                                            jsonObject.getString("child_id"),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("location")).trim(),
                                            jsonObject.getString("profile_pic").trim(),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("aboutMe")).trim(),
                                            jsonObject.getString("type").trim(),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("field")),
                                            jsonObject.getString("gender"),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("heightft")).trim(),
                                            Constants.unescapeSpecialCharacters(jsonObject.getString("heightin")).trim(),
                                            jsonObject.getString("person_id")
                                            ));

                                    myAdapter = new SwipeAdapter(Swipes.this, modelArrayList, Swipes.this);
                                    viewPager.setAdapter(myAdapter);
//                                    viewPager.setPadding(100, 0, 100, 0);

//                                    usernames.add(jsonObject.getString("username"));
//                                    ages.add(jsonObject.getString("age"));
//                                    profilePics.add(jsonObject.getString("profile_pic"));
//                                    aboutMes.add(jsonObject.getString("aboutMe"));
//                                    childIds.add(jsonObject.getString("child_id"));
//                                    races.add(jsonObject.getString("race"));
//                                    religions.add(jsonObject.getString("religion"));
//                                    types.add(jsonObject.getString("type"));
//                                    fields.add(jsonObject.getString("field"));
//                                    genders.add(jsonObject.getString("gender"));
//                                    heightFts.add(jsonObject.getString("heightft"));
//                                    heightIns.add(jsonObject.getString("heightin"));
//                                    locations.add(jsonObject.getString("location"));
//                                    personIds.add(jsonObject.getString("person_id"));
                                }

                                if (modelArrayList.size()==0){
                                    if (Constants.typee.equals("Parent")){
                                        Toaster.show(Swipes.this,"You have swiped on all all available matches\nThank You for using Pam");

                                    }else{
//                                            Toaster.show();

                                    }
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

//        child.makeRequest(Swipes.this);
        ;


//        loadCards();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                String title = modelArrayList.get(position).getTitle();
                //actionBar.setTitle(title);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


//    private void loadCards() {
//        modelArrayList = new ArrayList<>();
//
//        for (int ii = 0; ii< usernames.size();ii++) {
//            ChildInfo childInfo = Child.retrieveChildInfo(ii);
//            if (childInfo != null) {
//                modelArrayList.add(childInfo);
//            }
//        }
//
//        myAdapter = new SwipeAdapter(this, modelArrayList);
//        viewPager.setAdapter(myAdapter);
//        viewPager.setPadding(100, 0, 100, 0);
//    }
}