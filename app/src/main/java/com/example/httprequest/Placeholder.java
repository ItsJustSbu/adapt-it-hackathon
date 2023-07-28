package com.example.httprequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Placeholder extends AppCompatActivity {

    static OkHttpClient client =  new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);

        Toaster.show(Placeholder.this,Constants.entity_partner);

        TextView textView = findViewById(R.id.word);

        ImageView b =findViewById(R.id.ima);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String[] values = {};


                RequestBody requestBody = new FormBody.Builder()
                        .add("entity_id", Constants.entity_partner)
                        .build();

                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/getAllKids.php")
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
                                        // Parse the JSON response
                                        JSONArray jsonArray = new JSONArray(d);

                                        // Create ArrayLists to store the extracted data
                                        ArrayList<String> usernames = new ArrayList<>();
                                        ArrayList<Integer> ages = new ArrayList<>();
                                        ArrayList<String> profilePics = new ArrayList<>();
                                        ArrayList<String> aboutMes = new ArrayList<>();
                                        ArrayList<Integer> childIds = new ArrayList<>();
                                        ArrayList<String> races = new ArrayList<>();
                                        ArrayList<String> religions = new ArrayList<>();
                                        ArrayList<String> types = new ArrayList<>();
                                        ArrayList<String> fields = new ArrayList<>();
                                        ArrayList<String> genders = new ArrayList<>();
                                        ArrayList<String> heightFts = new ArrayList<>();
                                        ArrayList<String> heightIns = new ArrayList<>();
                                        ArrayList<String> locations = new ArrayList<>();
                                        ArrayList<Integer> personIds = new ArrayList<>();

                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                                            usernames.add(Constants.unescapeSpecialCharacters(jsonObject.getString("username")));
                                            ages.add(jsonObject.getInt("age"));
                                            profilePics.add(jsonObject.getString("profile_pic"));
                                            aboutMes.add(Constants.unescapeSpecialCharacters(jsonObject.getString("aboutMe")));
                                            childIds.add(jsonObject.getInt("child_id"));
                                            races.add(Constants.unescapeSpecialCharacters(jsonObject.getString("race")));
                                            religions.add(Constants.unescapeSpecialCharacters(jsonObject.getString("religion")));
                                            types.add(jsonObject.getString("type"));
                                            fields.add(Constants.unescapeSpecialCharacters(jsonObject.getString("field")));
                                            genders.add(jsonObject.getString("gender"));
                                            heightFts.add(Constants.unescapeSpecialCharacters(jsonObject.getString("heightft")));
                                            heightIns.add(Constants.unescapeSpecialCharacters(jsonObject.getString("heightin")));
                                            locations.add(Constants.unescapeSpecialCharacters(jsonObject.getString("location")));
                                            personIds.add(jsonObject.getInt("person_id"));
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }



                                    textView.setText(d);
                                }
                            });
                        }
                    }
                });
            }
        });


    }
}