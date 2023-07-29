package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class nfcTransmitterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_transmitter_page);

        Animation breathingAnimation = AnimationUtils.loadAnimation(this, R.anim.breathing_animation);
        ImageView nfcpic = findViewById(R.id.nfcpic);

        Toaster.show(nfcTransmitterPage.this,"NFC is now active");

        nfcpic.startAnimation(breathingAnimation);

        ImageView backbtn =  findViewById(R.id.backnfc);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nfcTransmitterPage.this,possibleEmergencies.class));
            }
        });

    }
}