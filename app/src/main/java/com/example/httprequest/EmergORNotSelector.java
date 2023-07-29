package com.example.httprequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class EmergORNotSelector extends AppCompatActivity {

    private boolean isSpeakerOn = false;
    private String phoneNumber = "0745154099";
    private String message = "0834620294kjsbdkjasbdih";

    //    private TextToSpeech textToSpeech;

    // the text to speech idea to read out a users personal info doesnt work
    // because the phone call is using the mic and speaker hense
    // both text to speech and a phone call could not be made at the same time
    // which was depressing :(

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emerg_ornot_selector);

        Animation animation = AnimationUtils.loadAnimation(EmergORNotSelector.this, R.anim.bounce);



        LinearLayout emergency = findViewById(R.id.emergencySelected);
        LinearLayout noemergency = findViewById(R.id.nonEmergencySelected);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emergency.startAnimation(animation);
//                emergency.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
//                noemergency.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));


                // a delay was added to let everthing happen sequencially

                new Handler().postDelayed(() -> {
                    emergencySmsAndPhoneCall();
                }, 200);

                new Handler().postDelayed(() -> {
                    startActivity( new Intent(EmergORNotSelector.this,possibleEmergencies.class));
                }, 2000);


            }
        });

        noemergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noemergency.startAnimation(animation);
//                noemergency.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
//                emergency.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));


                // a delay was added to let everthing happen sequencially
                new Handler().postDelayed(() -> {
                    startActivity( new Intent(EmergORNotSelector.this,possibleEmergencies.class));
                }, 200);


            }
        });


    }


    private void emergencySmsAndPhoneCall(){

        // the following is used to send an sms (first permission is verified)
        // from the code i used to make the phone call.
        // i changed up the permissions and found a way to make an sms occur

        if (ContextCompat.checkSelfPermission(EmergORNotSelector.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EmergORNotSelector.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toaster.show(EmergORNotSelector.this,"Sms has been sent to provide your doctor with your medical information");
        }

        // this is guided code from a stackoverflow post

        if (ContextCompat.checkSelfPermission(EmergORNotSelector.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EmergORNotSelector.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {

//            // sets the call to speaker mode to allow the conversation to be audible
//
//            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            if (audioManager != null) {
//                audioManager.setMode(AudioManager.MODE_IN_CALL);
//                isSpeakerOn = !isSpeakerOn;
//                audioManager.setSpeakerphoneOn(isSpeakerOn);
//            }

            // it didnt work :(

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            Toaster.show(EmergORNotSelector.this,"Phone call is being made");
            startActivity(intent);
        }
    }



    // permissions are checked whenever we use something from manifest
    // hense an onRequestPermissionsResult is required
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                emergencySmsAndPhoneCall();
            }
        }


    }

}