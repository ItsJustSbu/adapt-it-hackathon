package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class cardhealthinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardhealthinfo);

        ImageView recpic = findViewById(R.id.recoverpic);

        TextView title = findViewById(R.id.cardinfotitle);

        title.setText(Constants.pop.getText());

        LinearLayout sympcontainer = findViewById(R.id.sympcontainer);
        LinearLayout treatcontainer =findViewById(R.id.treatcontainer);

// .trim.capfirst

        ImageView backbtn = findViewById(R.id.backcardinfo);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cardhealthinfo.this, possibleEmergencies.class));
            }
        });


        if (Constants.pop.getPicVis()){
            recpic.setVisibility(View.VISIBLE);
            recpic.setImageResource(Constants.pop.getImageResource());
        }else{
            recpic.setVisibility(View.GONE);
        }

        ArrayList<String> symptoms = Constants.pop.getSymptoms();

        ArrayList<String> treatment = Constants.pop.getTreatments();

        for (int i =0;i<symptoms.size();i++){

                TextView bulletTextView = new TextView(this);
                String textt =Constants.capFirst("\u2022  "+ symptoms.get(i).trim());
                bulletTextView.setText(textt);
                bulletTextView.setTextSize(18);
                sympcontainer.addView(bulletTextView);

        }

        for (int i =0;i<treatment.size();i++){

            TextView bulletTextView = new TextView(this);
            String textt =Constants.capFirst("\u2022  "+ treatment.get(i).trim());
            bulletTextView.setText(textt);
            bulletTextView.setTextSize(18);
            treatcontainer.addView(bulletTextView);

        }

    }
}