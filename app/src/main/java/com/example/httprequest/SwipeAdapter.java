package com.example.httprequest;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

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

public class SwipeAdapter extends PagerAdapter {
    private Context context;


    static OkHttpClient client = new OkHttpClient();
    private Activity activity;
    private ArrayList<ChildInfo> modelArrayList = new ArrayList<>();

    //constructor
    public SwipeAdapter(Context context, ArrayList<ChildInfo> modelArrayList, Activity activity) {
        this.context = context;
        this.activity= activity;
        this.modelArrayList = modelArrayList;
//        this.modelArrayList.add(new ChildInfo("Huziibee","18","Indian","Islam","10","Johannesburg",Integer.toString(R.drawable.user),"mooop","Student","Bsc moop","Male","6","5","20"));
    }

    @Override
    public int getCount() {
        return modelArrayList.size();//returns size of items in list
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.swipes_card_item, container, false);
        TextView username = view.findViewById(R.id.usernamesw);
        TextView age = view.findViewById(R.id.agesw);
        TextView field = view.findViewById(R.id.fieldsw);
        TextView location = view.findViewById(R.id.locationsw);
        TextView race = view.findViewById(R.id.racesw);
        TextView aboutMe = view.findViewById(R.id.aboutmesw);

        ImageView pfp = view.findViewById(R.id.prfpsw);





        ChildInfo model = modelArrayList.get(position);

        username.setText(model.getUsername());

        username.setText(model.getUsername());
        age.setText(model.getAge());
        field.setText(model.getField());
        location.setText(model.getLocation());
        race.setText(model.getRace());
        aboutMe.setText(model.getAboutMe());

        pfp.setImageResource(Integer.parseInt(model.getProfilePic()));

        LinearLayout dislike = view.findViewById(R.id.dislike);
        LinearLayout like = view.findViewById(R.id.liked);




        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
                dislike.startAnimation(animation);
                String entity_id = Constants.entity_partner;
                String entity_opinion = "1";
                String others_id = model.getPersonId();

                RequestBody requestBody = new FormBody.Builder()
                        .add("entity_id", entity_id)
                        .add("entity_opinion", entity_opinion)
                        .add("others_id", others_id)
                        .build();

                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/swipeyMatches.php")
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
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(d);
                                        String mess = jsonObject.getString("message");
                                        if (mess.contains("You have already swiped")){

                                            Toaster.show(context,"You have already swiped on this person");
                                        }else{
                                            Toaster.show(context,"Disliked");
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }


                                }
                            });

                        }
                    }
                });
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
                like.startAnimation(animation);
                String entity_id = Constants.entity_partner;
                String entity_opinion = "1";
                String others_id = model.getPersonId();
                // perform request here

                RequestBody requestBody = new FormBody.Builder()
                        .add("entity_id", entity_id)
                        .add("entity_opinion", entity_opinion)
                        .add("others_id", others_id)
                        .build();

                Request request1 = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/swipeyMatches.php")
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
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(d);
                                        String mess = jsonObject.getString("message");
                                        if (mess.contains("You have already swiped")){

                                            Toaster.show(context,"You have already swiped on this person");
                                        }else{
                                            Toaster.show(context,"Liked");
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }


                                }
                            });

                        }
                    }
                });
            }
        });







        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toaster.show(context,model.getUsername() + "\n"+ model.getAboutMe());
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem (@NonNull ViewGroup container, int position, @NonNull Object object) {
        container. removeView((View)object);
    }


}