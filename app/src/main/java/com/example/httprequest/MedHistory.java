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
        TextView specifyMed = (TextView) findViewById(R.id.specifyTV);
        TextView specifyAlgs = (TextView) findViewById(R.id.specifyTV2);
        EditText specifyingAlgs = (EditText) findViewById(R.id.specifyET2);
        EditText specifyingMed = (EditText) findViewById(R.id.specifyET);
        Button continueBtn = (Button) findViewById(R.id.medHisBtn);

        ArrayList<String> medRecord = new ArrayList<>();
        specifyingMed.setVisibility(View.INVISIBLE);
        specifyingAlgs.setVisibility(View.INVISIBLE);

        medicating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    medRecord.add("On Medication ");
                    specifyingMed.setVisibility(View.VISIBLE);
                    specifyMed.setText("Specify the medication which you are currently taking below");

                } else {
                    medRecord.add("Not on any Medication ");
                    specifyMed.setText("");
                    specifyingMed.setVisibility(View.INVISIBLE);

                }
            }
        });
         allergies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                     medRecord.add("Has Allergies ");
                     specifyingAlgs.setVisibility(View.VISIBLE);
                     specifyAlgs.setText("Specify what you are allergic to below");

                 } else {
                     medRecord.add("No Allergies ");
                     specifyAlgs.setText("");
                     specifyingAlgs.setVisibility(View.INVISIBLE);
                 }

             }
         });

         diabetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                     medRecord.add("Is Diabetic ");
                 }
                 else {
                     medRecord.add("Not Diabetic ");
                 }
             }
         });

        pregnancy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    medRecord.add("Is Pregnant ");
                }
                else {
                    medRecord.add("Not Pregnant ");
                }
            }
        });

        pregnancy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    medRecord.add("Is HIV/AIDs positive ");
                }
                else {
                    medRecord.add("Is HIV/AIDs+ negative ");
                }
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
                // send array to firebase!!
                Map<String, Object> addConditions = new HashMap<>();
                addConditions.put("uid", auth.getUid());
                addConditions.put("conditions",medRecord);

                db.collection("medicalHistory").add(addConditions)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toaster.show(MedHistory.this,"successfully recorded");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toaster.show(MedHistory.this,"failed to record history, try again");
                            }
                        });

            }
        });
    }
}


