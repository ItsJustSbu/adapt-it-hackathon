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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String id = ID.getText().toString();
                String bloodtype = BloodType.getText().toString();
                String medAid = MedAid.getText().toString();
                String docNum = DocNum.getText().toString();
                openMedHistory();

                Map<String, Object> user = new HashMap<>();
                user.put("id", id);
                user.put("bloodtype", bloodtype);
                user.put("medAid", medAid);
                user.put("docNum", docNum);

                db.collection("personalInformation")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toaster.show(hidden1.this,"successful");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toaster.show(hidden1.this,"failed");
                            }
                        });
            }
            public void openMedHistory(){
                Intent intent = new Intent(hidden1.this, MedHistory.class);
                startActivity(intent);
            }

        });
    }
}

