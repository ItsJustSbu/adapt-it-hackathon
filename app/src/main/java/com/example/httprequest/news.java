package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class news extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ImageView pfp =findViewById(R.id.newsspfp);

        matchInfo mode = Constants.matchesItems.get(Constants.mat);

        ImageView back = findViewById(R.id.newsback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(news.this,MainActivity.class));
            }
        });

        pfp.setImageResource(Integer.parseInt(mode.getProfilePic()));

        TextView username = findViewById(R.id.newsusername);

        TextView age = findViewById(R.id.newsage);
        TextView field = findViewById(R.id.newsfield);
        TextView email = findViewById(R.id.newsemail);
        TextView phone = findViewById(R.id.newsphone);
        TextView location = findViewById(R.id.newslocation);
        TextView race = findViewById(R.id.newsrace);
        TextView aboutMe = findViewById(R.id.newsaboutMe);

        username.setText(mode.getUsername());
        age.setText(mode.getAge());
        field.setText(mode.getField());
        email.setText(mode.getEmail());
        phone.setText(mode.getPhoneNumber());
        location.setText(mode.getLocation());
        race.setText(mode.getRace());
        aboutMe.setText(mode.getAboutMe());

// Now you can use the assigned TextView objects as needed


    }
}