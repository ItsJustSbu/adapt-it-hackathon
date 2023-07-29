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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class hidden1 extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden1);

        TextInputLayout textInputLayout = findViewById(R.id.fieldOf);

        EditText ID = findViewById(R.id.patientID);
        EditText BloodType = findViewById(R.id.bloodType);
        EditText MedAid = findViewById(R.id.medAid);
        EditText DocNum = findViewById(R.id.docNumber);

        Button next = findViewById(R.id.hid1next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = ID.getText().toString();
                String bloodtype = BloodType.getText().toString();
                String medAid = MedAid.getText().toString();
                String docNum = DocNum.getText().toString();

            }
        });
    }
}

