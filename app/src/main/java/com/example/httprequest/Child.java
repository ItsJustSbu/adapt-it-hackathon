package com.example.httprequest;


import android.app.Activity;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Child {
    static OkHttpClient client = new OkHttpClient();
    String personid = Constants.person_id;
//    public static ArrayList<String> idList = new ArrayList<>();
//    public static ArrayList<String> usernameList = new ArrayList<>();
//    public static ArrayList<String> emailList = new ArrayList<>();
//    public static ArrayList<Integer> ageList = new ArrayList<>();
//    public static ArrayList<String> phoneNumberList = new ArrayList<>();
//    public static ArrayList<String> aboutMeList = new ArrayList<>();

    // Create ArrayLists to store the extracted data
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> ages = new ArrayList<String>();
    public static ArrayList<String> profilePics = new ArrayList<>();
    public static ArrayList<String> aboutMes = new ArrayList<>();
    public static ArrayList<String> childIds = new ArrayList<String>();
    public static ArrayList<String> races = new ArrayList<>();
    public static ArrayList<String> religions = new ArrayList<>();
    public static ArrayList<String> types = new ArrayList<>();
    public static ArrayList<String> fields = new ArrayList<>();
    public static ArrayList<String> genders = new ArrayList<>();
    public static ArrayList<String> heightFts = new ArrayList<>();
    public static ArrayList<String> heightIns = new ArrayList<>();
    public static ArrayList<String> locations = new ArrayList<>();
    public static ArrayList<String> personIds = new ArrayList<String>();


    public void makeRequest(Activity activity) {

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
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONArray jsonArray = new JSONArray(d);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    usernames.add(jsonObject.getString("username"));
                                    ages.add(jsonObject.getString("age"));
                                    profilePics.add(jsonObject.getString("profile_pic"));
                                    aboutMes.add(jsonObject.getString("aboutMe"));
                                    childIds.add(jsonObject.getString("child_id"));
                                    races.add(jsonObject.getString("race"));
                                    religions.add(jsonObject.getString("religion"));
                                    types.add(jsonObject.getString("type"));
                                    fields.add(jsonObject.getString("field"));
                                    genders.add(jsonObject.getString("gender"));
                                    heightFts.add(jsonObject.getString("heightft"));
                                    heightIns.add(jsonObject.getString("heightin"));
                                    locations.add(jsonObject.getString("location"));
                                    personIds.add(jsonObject.getString("person_id"));
                                }

                                Toaster.show(activity,"Completed");


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
    public interface ChildDataListener {
        void onDataLoaded();
    }
    private ChildDataListener childDataListener;
    public void setChildDataListener(ChildDataListener listener) {
        childDataListener = listener;
    }

    private void notifyDataLoaded() {
        if (childDataListener != null) {
            childDataListener.onDataLoaded();
        }
    }
    public Child(){}

    public static ChildInfo retrieveChildInfo(int index) {


            return new ChildInfo(
                usernames.get(index),
                ages.get(index),
                profilePics.get(index),
                aboutMes.get(index),
                childIds.get(index),
                races.get(index),
                religions.get(index),
                types.get(index),
                fields.get(index),
                genders.get(index),
                heightFts.get(index),
                heightIns.get(index),
                locations.get(index),
                personIds.get(index)
        );
//            return null;

    }
//        return null;

    }