package com.example.httprequest;
//
//import static com.example.httprequest.Constants.wallpaperItems;

import android.app.Activity;

import androidx.annotation.NonNull;

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

public class befnewsArray {

private Activity activity;
    private static befnewsArray instance1;


    static OkHttpClient client = new OkHttpClient();
    private befnewsArray(Activity activity){
        this.activity=activity;
//        wallpaperItems.add(new WallpaperItem(R.drawable.random25));
//        wallpaperItems.add(new WallpaperItem(R.drawable.hobby13));
//        wallpaperItems.add(new WallpaperItem(R.drawable.random8));
//        wallpaperItems.add(new WallpaperItem(R.drawable.random29));
//        wallpaperItems.add(new WallpaperItem(R.drawable.landscape10));
//        wallpaperItems.add(new WallpaperItem(R.drawable.random5));
//        wallpaperItems.add(new WallpaperItem(R.drawable.random12));
//        wallpaperItems.add(new WallpaperItem(R.drawable.sport1));
//        wallpaperItems.add(new WallpaperItem(R.drawable.sport2));
//        wallpaperItems.add(new WallpaperItem(R.drawable.landscape3));
//
//        wallpaperItems.add(new WallpaperItem(R.drawable.beach1));
//        wallpaperItems.add(new WallpaperItem(R.drawable.landscape16));
//        wallpaperItems.add(new WallpaperItem(R.drawable.landscape24));
//        wallpaperItems.add(new WallpaperItem(R.drawable.job1));
//        wallpaperItems.add(new WallpaperItem(R.drawable.job2));
//        wallpaperItems.add(new WallpaperItem(R.drawable.job4));
//        wallpaperItems.add(new WallpaperItem(R.drawable.job6));
//        wallpaperItems.add(new WallpaperItem(R.drawable.hobby1));
//        wallpaperItems.add(new WallpaperItem(R.drawable.hobby6));
//        wallpaperItems.add(new WallpaperItem(R.drawable.hobby10));


    }


    public static synchronized befnewsArray getInstance1(Activity activity) {
//        this.activity=activity;
        if (instance1 == null) {
            instance1 = new befnewsArray(activity);
        }
        return instance1;
    }


    public ArrayList<newsitem> getImageList1() {

        return Constants.matchespics;
    }




}
