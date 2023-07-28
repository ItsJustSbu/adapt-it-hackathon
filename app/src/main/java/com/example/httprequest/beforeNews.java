package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class beforeNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_news);

        RecyclerView wallpaperRecyclerView = findViewById(R.id.wallpaperView1);
        wallpaperRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        newsAdapter adapter = new newsAdapter(befnewsArray.getInstance1(beforeNews.this).getImageList1());
        adapter.setOnItemClickListener(this::onItemClick);

        wallpaperRecyclerView.setAdapter(adapter);

        ImageView back = findViewById(R.id.befback);

        if (Constants.matchesItems.size()==0){
            if (Constants.typee.equals("Parent")){
                Toaster.show(beforeNews.this,Constants.entityUsername+" has no matches yet");

            }else{
                Toaster.show(beforeNews.this,"You have no matches yet");

            }
             }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(beforeNews.this,MainActivity.class));
            }
        });
    }

    public void onItemClick(int position) {

        Constants.mat=position;
        startActivity(new Intent(beforeNews.this,news.class));

//        newsitem selectedWallpaper = befnewsArray.getInstance1(beforeNews.this).getImageList1().get(position);

        // remove this toaster
//        Toast.makeText(this, "Selected image index: " + position, Toast.LENGTH_SHORT).show();

//        Constants.pfpindexx = position;

//        Constants.temppfp = Constants.wallpaperItems.get(Constants.pfpindexx).getImage();
//
//        Toaster.show(beforeNews.this, Constants.pfpindexx + "");
//        startActivity(new Intent(beforeNews.this,news.class));
    }
}