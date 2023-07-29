package com.example.httprequest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
public class MedHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_history);

        Switch medicating = findViewById(R.id.switch1);
        Switch diabetic = findViewById(R.id.switch2);
        Switch hivAids = findViewById(R.id.switch3);
        Switch allergies = findViewById(R.id.switch4);
        Switch pregnancy = findViewById(R.id.switch5);

    }
}


