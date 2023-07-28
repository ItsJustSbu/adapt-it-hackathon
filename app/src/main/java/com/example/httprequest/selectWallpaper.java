package com.example.httprequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class selectWallpaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wallpaper);

        RecyclerView wallpaperRecyclerView = findViewById(R.id.wallpaperView);
        wallpaperRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        WallpaperAdapter adapter = new WallpaperAdapter(ImageArray.getInstance().getImageList());
        adapter.setOnItemClickListener(this::onItemClick);
        // what do i put

        wallpaperRecyclerView.setAdapter(adapter);

        ImageView back = findViewById(R.id.backwallp);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(selectWallpaper.this,ProfileActivity.class));
            }
        });
    }


    public void onItemClick(int position) {

        WallpaperItem selectedWallpaper = ImageArray.getInstance().getImageList().get(position);

//        Toast.makeText(this, "Selected image index: " + position, Toast.LENGTH_SHORT).show();

        Constants.pfpindexx = position;

        Constants.temppfp = Constants.wallpaperItems.get(Constants.pfpindexx).getImage();

//        Toaster.show(selectWallpaper.this, Constants.pfpindexx + "");
        startActivity(new Intent(selectWallpaper.this,ProfileActivity.class));
    }
}