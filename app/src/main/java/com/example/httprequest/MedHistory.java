package com.example.httprequest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MedHistory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_history);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Switch medicating = (Switch) findViewById(R.id.switch1);
        Switch diabetic = (Switch) findViewById(R.id.switch2);
        Switch hivAids = (Switch) findViewById(R.id.switch3);
        Switch allergies = (Switch) findViewById(R.id.switch4);
        Switch pregnancy = (Switch) findViewById(R.id.switch5);
        TextInputLayout specifyMed = findViewById(R.id.specifyTV);
        TextInputLayout specifyAlgs = findViewById(R.id.specifyTV2);
        EditText specifyingAlgs = (EditText) findViewById(R.id.specifyET2);
        EditText specifyingMed = (EditText) findViewById(R.id.specifyET);
        Button continueBtn = (Button) findViewById(R.id.medHisBtn);






        ArrayList<String> medRecord = new ArrayList<>();
        specifyingMed.setVisibility(View.GONE);
        specifyingAlgs.setVisibility(View.GONE);

        medicating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String Medicating = "Not on Medication";
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                   Medicating = "Is On Medication";
                    specifyingMed.setVisibility(View.VISIBLE);
                    specifyMed.setVisibility(View.VISIBLE);


                } else {
                    Medicating = "Not on Medication";
                    specifyingMed.setVisibility(View.GONE);
                    specifyMed.setVisibility(View.GONE);

                }
                medRecord.add(Medicating);
            }
        });
         allergies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             String Allergies = "No Allergies";
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                     Allergies = "Has Allergies ";
                     specifyingAlgs.setVisibility(View.VISIBLE);
                     specifyAlgs.setVisibility(View.VISIBLE);
//                     specifyAlgs.setText("Specify what you are allergic to below");

                 } else {
                     Allergies = "No Allergies";
//                     specifyAlgs.setText("");
                     specifyAlgs.setVisibility(View.GONE);
                     specifyingAlgs.setVisibility(View.GONE);
                 }
                 medRecord.add(Allergies);
             }
         });

         diabetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             String Diabetic = "Not Diabetic";

             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                     Diabetic = "Is Diabetic";
                 }
                 else {
                     Diabetic = "Not Diabetic";
                 }
                 medRecord.add(Diabetic);
             }
         });

        pregnancy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String Pregnancy= "Not pregnant";
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    Pregnancy = "Is Pregnant ";
                }
                else {
                    Pregnancy= "Not pregnant";
                }
                medRecord.add(Pregnancy);
            }
        });

        hivAids.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String Hiv = "HIV/AIDs negative ";
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    String Hiv = "Is HIV/AIDs positive ";
                }
                else {
                    String Hiv = "HIV/AIDs negative ";
                }
                medRecord.add(Hiv);
            }
        });



        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!specifyingMed.getText().toString().isEmpty()) {
                    medRecord.add(specifyingMed.getText().toString());
                }
                if(!specifyingAlgs.getText().toString().isEmpty()) {
                    medRecord.add(specifyingAlgs.getText().toString());
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();
                // send array to firebase
                Map<String, Object> addConditions = new HashMap<>();
                addConditions.put("uid", auth.getUid());
                addConditions.put("conditions",medRecord);

                db.collection("medicalHistory").add(addConditions)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toaster.show(MedHistory.this,"Information added successfully");
                                Intent intent = new Intent(MedHistory.this, Login .class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toaster.show(MedHistory.this,"Information was not added successfully, try again!");
                            }
                        });

            }
        });
    }
}


