package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class possibleEmergencies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<CardItem> cardItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possible_emergencies);

        recyclerView = findViewById(R.id.emergenciesView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        cardItemList = new ArrayList<>();
        // Add your card items here, you can replace the images and text with your data
        cardItemList.add(new CardItem(R.drawable.user, "Card 1"));
        cardItemList.add(new CardItem(R.drawable.no, "Card 2"));
        cardItemList.add(new CardItem(R.drawable.ambulance, "Card 3"));
        // Add more card items if needed...

        cardAdapter = new CardAdapter(cardItemList);
        cardAdapter.setOnItemClickListener(this::onItemClick);
        recyclerView.setAdapter(cardAdapter);
    }

    public void onItemClick(int position) {

        Toaster.show(possibleEmergencies.this,"The position is : "+position);
    }
}