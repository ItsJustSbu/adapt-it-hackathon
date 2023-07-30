package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class possibleEmergencies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possible_emergencies);

        Animation animation = AnimationUtils.loadAnimation(possibleEmergencies.this, R.anim.bounce);

        ImageView nfc = findViewById(R.id.nfc);
        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nfc.startAnimation(animation);
                startActivity(new Intent(possibleEmergencies.this,nfcTransmitterPage.class));
            }
        });

        ImageButton chatbotbtn = findViewById(R.id.btnchatbot);

        chatbotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatbotbtn.startAnimation(animation);
                startActivity(new Intent(possibleEmergencies.this,ChatActivity.class));
            }
        });

        ImageView backbtn = findViewById(R.id.possback);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(possibleEmergencies.this, EmergORNotSelector.class));
            }
        });

        recyclerView = findViewById(R.id.emergenciesView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        Constants.cardItemList = new ArrayList<>();

        // Create ArrayLists for symptoms and treatments for each topic
        ArrayList<String> shockSymptoms = new ArrayList<>();
        shockSymptoms.add("Rapid week pulse (heart speeds up to pump reduced blood volume around the body)");
        shockSymptoms.add("Rapid, shallow breathing ");
        shockSymptoms.add("Cold, clammy, pale skin ");
        shockSymptoms.add("Decreased level of consciousness");
        shockSymptoms.add("Possible dilated pupils");
        shockSymptoms.add("Obvious causes (major bleeding, ect.)");
        shockSymptoms.add("Casualty may be thirsty");
        shockSymptoms.add("Casualty will be restless");
        shockSymptoms.add("Nausea and vomiting ");

        ArrayList<String> shockTreatments = new ArrayList<>();
        shockTreatments.add("play the casualty town in elevator legs about 30 centimetres if possible");
        shockTreatments.add("Calm and reassure the casualty. Also loosen any constricting or tight clothing ");
        shockTreatments.add("Maintain their body temperature with a light covering ");
        shockTreatments.add("If the casualty complaints of thirst, wet their lips if possible to relieve discomfort but DO NOT give them anything by mouth ");
        shockTreatments.add("Place the casualty in the recovery position ");
        shockTreatments.add("Arrange transport to the hospital via ambulance ");

        ArrayList<String> heartAttackSymptoms = new ArrayList<>();
        heartAttackSymptoms.add("Chest pains for crater than 10 minutes ");
        heartAttackSymptoms.add("grey skin ");
        heartAttackSymptoms.add("excessive sweating ");
        heartAttackSymptoms.add("May radiate to the left arm, neck ect.");
        heartAttackSymptoms.add("Symptoms of shock");

        ArrayList<String> heartAttackTreatments = new ArrayList<>();
        heartAttackTreatments.add("Place them in a semi-seated position ");
        heartAttackTreatments.add("DO NOT raise their legs as it will place strain on the heart ");
        heartAttackTreatments.add("Loosen tight clothing ");
        heartAttackTreatments.add("Call emergency services");



        ArrayList<String> strokeSymptoms = new ArrayList<>();
        strokeSymptoms.add("Facial droop");
        strokeSymptoms.add("Arm drift (Ask the casualty to lift their arm )");
        strokeSymptoms.add("Slurred speech ");

        ArrayList<String> strokeTreatments = new ArrayList<>();
        strokeTreatments.add("Place them in a semi-seated position or recovery position with the injured side down ");
        strokeTreatments.add("Loosen tight clothing ");
        strokeTreatments.add("Call for emergency services immediately ");


// Add data for other topics
        ArrayList<String> asphyxiaSymptoms = new ArrayList<>();
        asphyxiaSymptoms.add("Difficulty breathing - rate and depth increases ");
        asphyxiaSymptoms.add("Noisy breathing - snoring or bubbling ");
        asphyxiaSymptoms.add("Decreasing level of consciousness ");
        asphyxiaSymptoms.add("Veins around the neck may stick out due to difficulty breathing ");
        asphyxiaSymptoms.add("Skin colour may become blue (cyanosis)");
        asphyxiaSymptoms.add("Headache");

        ArrayList<String> asphyxiaTreatments = new ArrayList<>();
        asphyxiaTreatments.add("Remove the cause of blockage");
        asphyxiaTreatments.add("Remove casualty to fresh air ");
        asphyxiaTreatments.add("Monitor and record vitals ");
        asphyxiaTreatments.add("remove to hospital if necessary ");

        ArrayList<String> externalBleedingSymptoms = new ArrayList<>();
        externalBleedingSymptoms.add("Pulse rate begins to increase but becomes weak ");
        externalBleedingSymptoms.add("Casualty becomes pale, cold and clammy ");
        externalBleedingSymptoms.add("Casualty may be thirsty ");
        externalBleedingSymptoms.add("Decrease level of consciousness with possible fainting ");
        externalBleedingSymptoms.add("Possible blood loss ");

        ArrayList<String> externalBleedingTreatments = new ArrayList<>();
        externalBleedingTreatments.add("Rest the casualty which will slow down their heartbeat ");
        externalBleedingTreatments.add("Use gravity to assist you in controlling bleeding by elevating the affected area if possible ");
        externalBleedingTreatments.add("Use a pressure bandage to apply direct pressure ");

        ArrayList<String> internalBleedingSymptoms = new ArrayList<>();
        internalBleedingSymptoms.add("Casualty goes into shock, often without any evidence of external bleeding ");
        internalBleedingSymptoms.add("History of the event ");
        internalBleedingSymptoms.add("Vomiting ");
        internalBleedingSymptoms.add("Pain and rigidity in the abdomen if this is the site of bleeding ");
        internalBleedingSymptoms.add("Severe education and anxiety / restlessness ");

        ArrayList<String> internalBleedingTreatments = new ArrayList<>();
        internalBleedingTreatments.add("Read the casualty for shock to prevent the bleed from speeding up ");
        internalBleedingTreatments.add("Keep any specimens past or vomited if possible so it may assist the hospital ");
        internalBleedingTreatments.add("Remove casualty to the hospital immediately ");

        ArrayList<String> burnsSymptoms = new ArrayList<>();
        burnsSymptoms.add("Take note of the type of burn\nFirst degree burns Damages the top layer of the skin called the epidermis \nSecond degree burns damages the epidermis and dermis causing blisters\nThird degree burns are damages to all layers of the skin");
        burnsSymptoms.add("If there is a liquid, gas or powder on the casualty as this is a chemical burn");
        burnsSymptoms.add("If there is a visible wound where current entered and exited the body this is an electrical burn ");

        ArrayList<String> burnsTreatments = new ArrayList<>();
        burnsTreatments.add("If available apply \"BURNSHIELD\" ");
        burnsTreatments.add("If this is a chemical burn use PPE and run a gentle steady stream of cool tap water over the burn for 15 or more minutes ");
        burnsTreatments.add("If this is an electrical burn turn off the electrical power and treat for burns ");
        burnsTreatments.add("If in doubt, remove to hospital ");

        ArrayList<String> poisoningSymptoms = new ArrayList<>();
        poisoningSymptoms.add("History : A casualty or bystander tells you that poison was taken ");
        poisoningSymptoms.add("Information provided by the area eg. pill containers laying around ");
        poisoningSymptoms.add("In some cases, nausea and vomiting will be present ");
        poisoningSymptoms.add("Signs of asphyxia due to poisoning affecting the nervous system ");
        poisoningSymptoms.add("Casualty may have a smell of alcohol ");
        poisoningSymptoms.add("Sweating ");
        poisoningSymptoms.add("Hallucinations ");
        poisoningSymptoms.add("Pupils may constrict, dilate or slow to react to light ");
        poisoningSymptoms.add("Level of consciousness may drop ");

        ArrayList<String> poisoningTreatments = new ArrayList<>();
        poisoningTreatments.add("Contact the Poison Control centre on 0800 333 444 ");
        poisoningTreatments.add("Do not induce vomiting  ");
        poisoningTreatments.add("Treat the casualty for shock if necessary ");
        poisoningTreatments.add("Monitor and record vital signs closely ");
        poisoningTreatments.add("remove the casualty to a hospital preferably by ambulance ");



        // Create instances of CardItem for each topic and add them to Constants.cardItemList in one line
        Constants.cardItemList.add(new CardItem(R.drawable.shock, "Shock", shockSymptoms, shockTreatments, true));
        Constants.cardItemList.add(new CardItem(R.drawable.no, "Heart Attack", heartAttackSymptoms, heartAttackTreatments, false));
        Constants.cardItemList.add(new CardItem(R.drawable.recovery, "Stroke", strokeSymptoms, strokeTreatments, true));
        Constants.cardItemList.add(new CardItem(R.drawable.ambulance, "Asphyxia", asphyxiaSymptoms, asphyxiaTreatments, false));
        Constants.cardItemList.add(new CardItem(R.drawable.ambulance, "External Bleeding", externalBleedingSymptoms, externalBleedingTreatments, false));
        Constants.cardItemList.add(new CardItem(R.drawable.ambulance, "Internal Bleeding", internalBleedingSymptoms, internalBleedingTreatments, false));
        Constants.cardItemList.add(new CardItem(R.drawable.burnshield, "Burns", burnsSymptoms, burnsTreatments, true));
        Constants.cardItemList.add(new CardItem(R.drawable.ambulance, "Poisoning", poisoningSymptoms, poisoningTreatments, false));


        cardAdapter = new CardAdapter(Constants.cardItemList);
        cardAdapter.setOnItemClickListener(this::onItemClick);
        recyclerView.setAdapter(cardAdapter);
    }

    public void onItemClick(int position) {

//        Toaster.show(possibleEmergencies.this,"The position is : "+position);

        Constants.pop = Constants.cardItemList.get(position);

        startActivity(new Intent(possibleEmergencies.this, cardhealthinfo.class));
    }
}