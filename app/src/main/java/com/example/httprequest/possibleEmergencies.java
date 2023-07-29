package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class possibleEmergencies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possible_emergencies);

        Animation animation = AnimationUtils.loadAnimation(possibleEmergencies.this, R.anim.bounce);

        ImageView nfc = findViewById(R.id.nfc);
        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nfc.startAnimation(animation);
                startActivity(new Intent(possibleEmergencies.this,nfcTransmitterPage.class));
            }
        });

        ImageButton chatbotbtn = findViewById(R.id.btnchatbot);

        chatbotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatbotbtn.startAnimation(animation);
                startActivity(new Intent(possibleEmergencies.this,ChatActivity.class));
            }
        });

        ImageView backbtn = findViewById(R.id.possback);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(possibleEmergencies.this, EmergORNotSelector.class));
            }
        });

        recyclerView = findViewById(R.id.emergenciesView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        Constants.cardItemList = new ArrayList<>();
        Constants.cardItemList.add(new CardItem(R.drawable.user, "Card 1", "Symptoms", "Treament"));
        Constants.cardItemList.add(new CardItem(R.drawable.no, "Card 2", "Symptoms", "Treament"));
        Constants.cardItemList.add(new CardItem(R.drawable.ambulance, "Card 3", "Symptoms", "Treament"));


        cardAdapter = new CardAdapter(Constants.cardItemList);
        cardAdapter.setOnItemClickListener(this::onItemClick);
        recyclerView.setAdapter(cardAdapter);
    }

    public void onItemClick(int position) {

        Toaster.show(possibleEmergencies.this,"The position is : "+position);
    }
}