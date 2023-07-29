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

import com.google.android.material.textfield.TextInputLayout;
public class MedHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_history);

        Switch medicating = (Switch) findViewById(R.id.switch1);
        Switch diabetic = (Switch) findViewById(R.id.switch2);
        Switch hivAids = (Switch) findViewById(R.id.switch3);
        Switch allergies = (Switch) findViewById(R.id.switch4);
        Switch pregnancy = (Switch) findViewById(R.id.switch5);
        TextView specify = (TextView) findViewById(R.id.specifyTV);
        EditText specifying = (EditText) findViewById(R.id.specifyET);

        medicating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                   specify.setText("");
//                } else {
//
//                }
            }

            }
        });

    }
}


