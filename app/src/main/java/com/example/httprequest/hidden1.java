package com.example.httprequest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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

                if(id.isEmpty() || bloodtype.isEmpty() || medAid.isEmpty() || docNum.isEmpty()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toaster.show(hidden1.this, "Incomplete Information");
                        }
                    });

                }

                // might need to set up API to validate id and phone number

                if(id.trim().length() != 13){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(hidden1.this, "Invalid ID", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                String bloo = bloodtype.trim().toUpperCase();

                if(bloo.length()>2 && !bloo.equals("O") && !bloo.equals("A") && !bloo.equals("B") && !bloo.equals("AB")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toaster.show(hidden1.this, "Invalid Blood Type");
                        }
                    });
                }

                else {

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
                                    Toaster.show(hidden1.this, "Information added successfully");
                                    Intent intent = new Intent(hidden1.this, MedHistory.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("MainActivity", "Error adding document", e);
                                    Toaster.show(hidden1.this, "Information was not added successfully");
                                }
                            });
                }
            }

        });
    }
}

