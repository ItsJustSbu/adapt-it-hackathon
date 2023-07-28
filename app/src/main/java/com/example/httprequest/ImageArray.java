package com.example.httprequest;

import static com.example.httprequest.Constants.wallpaperItems;

import java.util.ArrayList;

public class ImageArray {


        private static ImageArray instance;
        private ImageArray(){
                wallpaperItems.add(new WallpaperItem(R.drawable.random25));
                wallpaperItems.add(new WallpaperItem(R.drawable.hobby13));
                wallpaperItems.add(new WallpaperItem(R.drawable.random8));
                wallpaperItems.add(new WallpaperItem(R.drawable.random29));
                wallpaperItems.add(new WallpaperItem(R.drawable.landscape10));
                wallpaperItems.add(new WallpaperItem(R.drawable.random5));
                wallpaperItems.add(new WallpaperItem(R.drawable.random12));
                wallpaperItems.add(new WallpaperItem(R.drawable.sport1));
                wallpaperItems.add(new WallpaperItem(R.drawable.sport2));
                wallpaperItems.add(new WallpaperItem(R.drawable.landscape3));

                wallpaperItems.add(new WallpaperItem(R.drawable.beach1));
                wallpaperItems.add(new WallpaperItem(R.drawable.landscape16));
                wallpaperItems.add(new WallpaperItem(R.drawable.landscape24));
                wallpaperItems.add(new WallpaperItem(R.drawable.job1));
                wallpaperItems.add(new WallpaperItem(R.drawable.job2));
                wallpaperItems.add(new WallpaperItem(R.drawable.job4));
                wallpaperItems.add(new WallpaperItem(R.drawable.job6));
                wallpaperItems.add(new WallpaperItem(R.drawable.hobby1));
                wallpaperItems.add(new WallpaperItem(R.drawable.hobby6));
                wallpaperItems.add(new WallpaperItem(R.drawable.hobby10));

        }


        public static synchronized ImageArray getInstance() {
                if (instance == null) {
                        instance = new ImageArray();
                }
                return instance;
        }


        public ArrayList<WallpaperItem> getImageList() {
            return wallpaperItems;
        }




}
