package com.example.httprequest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingsActivity extends AppCompatActivity {


    static OkHttpClient client = new OkHttpClient();

    TextView location;
    TextView heightft;
    TextView race;
    TextView usernameee;
    TextView email;
    TextView password;
    TextView gender;
    TextView heightin;
    TextView religion;

    String locationString = "Location";
    String heightftString = "Height (ft.)";
    String raceString = "Race";
    String usernameString = "Username";
    String emailString = "Email address";
    String passwordString = "Password";
    String genderString = "Gender";
    String heightinString = "Height (in.)";
    String religionString = "Religion";

    String tmp1 = "uname";
    String tmp2 = "";
    String tmp4="";


    String chosen = "Location";

    TextView spinnerhead;

    Spinner spinner;
    TextInputLayout textve;


    boolean ifspin= false;


    Button saveChanges;
    LinearLayout linearLayout;
    String spin;

    String username;

    String spinfix;

//    boolean isreturnable =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView btnBack = findViewById(R.id.backsg);

        saveChanges = findViewById(R.id.btnRegisterstg);

        EditText textveeeiw = findViewById(R.id.tvv);

        Animation animation = AnimationUtils.loadAnimation(SettingsActivity.this, R.anim.bounce);



        textve = findViewById(R.id.tvvtit);




        location = findViewById(R.id.location);
        heightft = findViewById(R.id.heightftt);
        race = findViewById(R.id.race);
        usernameee = findViewById(R.id.usernamee);
        email = findViewById(R.id.emailll);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        heightin = findViewById(R.id.heightinn);
        religion = findViewById(R.id.religionn);

        spinnerhead =findViewById(R.id.spinnerheading);

        spinnerhead.setVisibility(View.GONE);


        LinearLayout top = findViewById(R.id.top);
        LinearLayout bottom = findViewById(R.id.bottom);
        linearLayout = findViewById(R.id.dyna);

        spinner = findViewById(R.id.spinner);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = parent.getItemAtPosition(position).toString();

                if (selectedValue.equals("Other.")) {
                    textve.setHint("Other. ("+chosen+")");
                    otherchosen();
                    textveeeiw.setText("");
//                    Toaster.show(SettingsActivity.this, textve.getHint().toString()+"Other.(Religion)");
                    if (textve.getHint().toString().equals("Other. (Location)")) {
                        tmp4 = "location1";
                    } else if (textve.getHint().toString().equals("Other. (Race)")) {
                        tmp4 = "race1";
                    } else if (textve.getHint().toString().equals("Other. (Religion)")) {
                        tmp4 = "religion1";
                    } else {
                        tmp4="";
                    }
                }else{
//                    justSpinner();
//                    if (isSpinnerPresent)
                    animateOutOtherchosen();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                location.startAnimation(animation);


                ArrayList<String> spinnerData = new ArrayList<>();
                setAllSelectorsToDefault();
                location.setBackground(getDrawable(R.drawable.settingsselected));
                spinnerData.add("Johannesburg");
                spinnerData.add("Pretoria");
                spinnerData.add("Cape Town");
                spinnerData.add("Durban");
                spinnerData.add("Bloemfontein");
                spinnerData.add("Other.");
                justSpinner();
                spinnerhead.setText(locationString);
                chosen=locationString;
                populateSpinner(spinnerData);
                ifspin= true;
                tmp2="location";
            }
        });

        heightft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightft.startAnimation(animation);
                ArrayList<String> spinnerData = new ArrayList<>();
                setAllSelectorsToDefault();
                heightft.setBackground(getDrawable(R.drawable.settingsselected));
                spinnerData.add("3");
                spinnerData.add("4");
                spinnerData.add("5");
                spinnerData.add("6");
                spinnerhead.setText(heightftString);
                spinnerData.add("7");
                justSpinner();
                populateSpinner(spinnerData);
                tmp2="hFT";
                ifspin= true;
            }
        });

        heightin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightin.startAnimation(animation);
                ArrayList<String> spinnerData = new ArrayList<>();
                setAllSelectorsToDefault();
                spinnerhead.setText(heightinString);
                justSpinner();
                heightin.setBackground(getDrawable(R.drawable.settingsselected));

                for (int i = 0; i <= 11; i++) {
                    spinnerData.add(i+"");
                }

                populateSpinner(spinnerData);
                tmp2 ="hIN";
                ifspin= true;
            }
        });


        race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                race.startAnimation(animation);
                ArrayList<String> spinnerData = new ArrayList<>();
                setAllSelectorsToDefault();
                race.setBackground(getDrawable(R.drawable.settingsselected));
                spinnerData.add("Indian");
                spinnerData.add("White");
                spinnerData.add("Coloured");
                spinnerData.add("Black");
                spinnerData.add("Asian");
                justSpinner();
                spinnerhead.setText(raceString);
                spinnerData.add("Other.");
                chosen=raceString;
                populateSpinner(spinnerData);
                tmp2="race";
                ifspin= true;
            }
        });



        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion.startAnimation(animation);
                ArrayList<String> spinnerData = new ArrayList<>();
                setAllSelectorsToDefault();
                religion.setBackground(getDrawable(R.drawable.settingsselected));
                spinnerData.add("Islam");
                spinnerData.add("Christianity");
                spinnerData.add("Hinduism");
                spinnerData.add("Judaism");
                spinnerData.add("Buddhism");
                spinnerData.add("Atheism");
                spinnerData.add("African Tradition Religion");
                spinnerData.add("Other.");
                justSpinner();
                spinnerhead.setText(religionString);
                chosen=religionString;
                populateSpinner(spinnerData);
                tmp2="religion";
                ifspin= true;
//
            }
        });

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender.startAnimation(animation);
                ArrayList<String> spinnerData = new ArrayList<>();
                setAllSelectorsToDefault();
                gender.setBackground(getDrawable(R.drawable.settingsselected));
                spinnerData.add("Male");
                spinnerData.add("Female");
                spinnerhead.setText(genderString);
                justSpinner();
                populateSpinner(spinnerData);
                tmp2="gender";
                ifspin= true;
            }
        });

        usernameee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameee.startAnimation(animation);
                setAllSelectorsToDefault();
                usernameee.setBackground(getDrawable(R.drawable.settingsselected));
                justTextView();
                textve.setHint(usernameString);
                textveeeiw.setText("");
                chosen=usernameString;
                tmp1="uname";

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.startAnimation(animation);
                justTextView();
                setAllSelectorsToDefault();
                email.setBackground(getDrawable(R.drawable.settingsselected));
                textve.setHint(emailString);
                textveeeiw.setText("");
                chosen=emailString;
                tmp1="email";

            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.startAnimation(animation);
                justTextView();
                setAllSelectorsToDefault();
                password.setBackground(getDrawable(R.drawable.settingsselected));
                textve.setHint(passwordString);
                textveeeiw.setText("");
                chosen=passwordString;
                tmp1="pword";
            }
        });

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();



//        TextInputLayout textInputLayout = findViewById(R.id.textInputLayoutsg);
//        TextInputEditText textInputEditText = findViewById(R.id.textVieww);
//
//// Set new hint
//        textInputEditText.setHint("New Hint");
//        textInputLayout.setVisibility(View.VISIBLE);





        if (Constants.typee.contains("Parent")){
            top.setVisibility(View.GONE);
            bottom.setVisibility(View.GONE);
            justTextView();
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topMargin = (int) (0 * getResources().getDisplayMetrics().density);
            linearLayout.setLayoutParams(layoutParams);
        }else {
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
            justTextView();
            layoutParams.startToStart = ConstraintLayout.LayoutParams.UNSET;
            layoutParams.topMargin = (int) (0 * getResources().getDisplayMetrics().density);
            linearLayout.setLayoutParams(layoutParams);
        }



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.isFirstTimeOnSEttings=true;
                startActivity(new Intent(SettingsActivity.this,MainActivity.class));
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = textveeeiw.getText().toString();

                spin = "";
                spinfix = "";
                String tmp = tmp1;
                String personid =Constants.person_id;
                String tmp3 = tmp2;
                String TMP4 = tmp4;


                if ( ifspin == true){
                    spin = spinner.getSelectedItem().toString();
                    spinfix = "true";
                }

                //check if valid
                if(!spinfix.equals("true")) {
                    if (tmp.equals("email")) {
                        username.replace(" ","");
                        if (username.isEmpty()) {
                            Toaster.show(SettingsActivity.this, "Email cannot be empty");
                            return;
                        }
                        if (!isValidEmail(username)) {
                            Toaster.show(SettingsActivity.this, "Email must be valid");
                            return;
                        }
                        username=Constants.escapeSpecialCharacters(username);
                    }

                    if (tmp.equals("uname") ) {
                        if (username.isEmpty()) {
                            Toaster.show(SettingsActivity.this, "Username cannot be empty");
                            return;
                        }
                        username=Constants.escapeSpecialCharacters(username);
                    }


                    if (tmp.equals("pword")) {
                        username.replace(" ","");
                        if (username.isEmpty()) {
                            Toaster.show(SettingsActivity.this, "Password cannot be empty");
                            return;
                        }
                        if(username.length() < 8){
                            Toaster.show(SettingsActivity.this, "Password requires a minimum of 8 characters");
                            return;
                        }
                        if (Constants.isPasswordValid(username)== false){
                            Toaster.show(SettingsActivity.this,"Password must contain both characters and numbers");
                            return;
                        }

                        username=Constants.escapeSpecialCharacters(Constants.encryptSHA256(username));
                    }
                }
                if (textve.getHint().toString().equals("Other. (Location)")) {                           //to check if other. is not empty
                    if (username.isEmpty()) {
                        Toaster.show(SettingsActivity.this, "Location cannot be empty");
                        return;
                    }
                    username=Constants.escapeSpecialCharacters(username);
                } else if (textve.getHint().toString().equals("Other. (Race)")) {
                    if (username.isEmpty()) {
                        Toaster.show(SettingsActivity.this, "Race cannot be empty");
                        return;
                    }
                    username=Constants.removeSpecialCharacters(username);
                } else if (textve.getHint().toString().equals("Other. (Religion)")) {
                    if (username.isEmpty()) {
                        Toaster.show(SettingsActivity.this, "Religion cannot be empty");
                        return;
                    }
                    username=Constants.removeSpecialCharacters(username);
                }



                RequestBody requestBody = new FormBody.Builder()
                        .add("username", Constants.escapeSpecialCharacters(username))
                        .add("spinfix",spinfix)
                        .add("personid",personid)
                        .add("spinner",spin)
                        .add("tmp3",tmp3)
                        .add("tmp4",TMP4)
                        .add("tmp",tmp).build();


                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2610990/changeSettings.php")
                        .post(requestBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (responseBody.contains("EAE")) {
                                        Toaster.show(SettingsActivity.this, "Email already exists. Please choose a different email");
                                    }
                                    if (responseBody.contains("S0")) {
                                        Toaster.show(SettingsActivity.this, "Email updated successfully");
                                        Constants.emaill=username;
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E0")){
                                        Toaster.show(SettingsActivity.this, "Error updating email");
                                    } else if (responseBody.contains("S1")) {
                                        Toaster.show(SettingsActivity.this, "Username updated successfully");
                                        Constants.username=Constants.capFirst(username);
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E1")){
                                        Toaster.show(SettingsActivity.this, "Error updating username");
                                    } else if (responseBody.contains("S2")) {
                                        Toaster.show(SettingsActivity.this, "Password updated successfully");
                                        Constants.password=username;
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E2")) {
                                        Toaster.show(SettingsActivity.this, "Error updating Password");
                                    } else if (responseBody.contains("S4")) {
                                        Toaster.show(SettingsActivity.this, "Location updated successfully");
                                        if (spinfix.contains("true")){
                                            Constants.location=spin;
                                        }else{
                                            Constants.location=Constants.capFirst(username);
                                        }
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E4")) {
                                        Toaster.show(SettingsActivity.this, "Error updating location");
                                    } else if (responseBody.contains("S5")) {
                                        Toaster.show(SettingsActivity.this, "Height (ft.) updated successfully");
                                        Constants.heightFt=spin;
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E5")) {
                                        Toaster.show(SettingsActivity.this, "Error updating height (ft.)");
                                    } else if (responseBody.contains("S6")) {
                                        Toaster.show(SettingsActivity.this, "Height (in.) updated successfully");
                                        Constants.heightIn=spin;
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E6")) {
                                        Toaster.show(SettingsActivity.this, "Error updating height (in.)");
                                    } else if (responseBody.contains("S7")) {
                                        Toaster.show(SettingsActivity.this, "Race updated successfully");
                                        if (spinfix.contains("true")){
                                            Constants.race=spin;
                                        }else{
                                            Constants.race=Constants.capFirst(username);
                                        }
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E7")) {
                                        Toaster.show(SettingsActivity.this, "Error updating race");
                                    } else if (responseBody.contains("S8")) {
                                        Toaster.show(SettingsActivity.this, "Religion updated successfully");
                                        if (spinfix.contains("true")){
                                            Constants.religion=spin;
                                        }else{
                                            Constants.religion=Constants.capFirst(username);
                                        }
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E8")) {
                                        Toaster.show(SettingsActivity.this, "Error updating religion");
                                    } else if (responseBody.contains("S9")) {
                                        Toaster.show(SettingsActivity.this, "Gender updated successfully");
                                        Constants.gender=spin;
                                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                                    } else if (responseBody.contains("E9")) {
                                        Toaster.show(SettingsActivity.this, "Error updating gender");
                                    }

                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toaster.show(SettingsActivity.this, "Error: " + response.code());
                                }
                            });
                        }
                    }
                });

                tmp2="";
                tmp4="";
                ifspin= false;

//                if(isreturnable){
//                    isreturnable=false;
//
//                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }

            }
        });
    }

    private void setAllSelectorsToDefault(){
        location.setBackground(getDrawable(R.drawable.settingsnotselected));
        heightft.setBackground(getDrawable(R.drawable.settingsnotselected));
        race.setBackground(getDrawable(R.drawable.settingsnotselected));
        usernameee.setBackground(getDrawable(R.drawable.settingsnotselected));
        email.setBackground(getDrawable(R.drawable.settingsnotselected));
        password.setBackground(getDrawable(R.drawable.settingsnotselected));
        gender.setBackground(getDrawable(R.drawable.settingsnotselected));
        heightin.setBackground(getDrawable(R.drawable.settingsnotselected));
        religion.setBackground(getDrawable(R.drawable.settingsnotselected));

    }

    private void populateSpinner (ArrayList<String> spinnerdataa){
        Spinner spinner =findViewById(R.id.spinner);
        spinner.setAdapter(null);
        List<String> spinnerData = spinnerdataa;

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void changetextviewhint(){

    }
    boolean isfromTexttoSpinner = true;
    boolean isfromSpinnertoText = true;

    private AnimationSet createSlideInAnimation() {

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(translateAnimation);

        return animationSet;
    }

    private AnimationSet createSlideOutAnimation() {
        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(translateAnimation);

        return animationSet;
    }

    private void animateInSpinnerheadAndTextve() {
        AnimationSet animationSet = createSlideInAnimation();

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                spinnerhead.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                textve.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

//        spinnerhead.startAnimation(animationSet);
        textve.startAnimation(animationSet);
    }

    private void animateOutSpinnerheadAndTextve() {
        AnimationSet animationSet = createSlideOutAnimation();

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                spinnerhead.setVisibility(View.GONE);
                textve.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

//        spinnerhead.startAnimation(animationSet);
        textve.startAnimation(animationSet);
    }

    private void animateInSpinnerheadAndSpinner() {
        AnimationSet animationSet = createSlideInAnimation();

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                spinnerhead.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                textve.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

//        spinnerhead.startAnimation(animationSet);
        spinner.startAnimation(animationSet);
    }

    private void animateOutSpinnerheadAndSpinner() {
        AnimationSet animationSet = createSlideOutAnimation();

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                spinnerhead.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

//        spinnerhead.startAnimation(animationSet);
        spinner.startAnimation(animationSet);
    }

    boolean isSpinnerPresent;
    boolean isTextViewPResent;

    boolean isOtherChosen;

    private void justTextView() {
        if (!Constants.isFirstTimeOnSEttings){
            if (isSpinnerPresent){
                animateOutSpinnerheadAndSpinner();
            }
            else{
                animateOutSpinnerheadAndTextve();
            }

            if(isOtherChosen){
                animateOutOtherchosen2();

                animateInSpinnerheadAndTextve();
//            animateTranslateUpAndDown();
            }else{
                animateInSpinnerheadAndTextve();
            }
//        animateOutSpinnerheadAndSpinner();
            isOtherChosen=false;
            isTextViewPResent=true;
        }
        Constants.isFirstTimeOnSEttings=false;

    }



    private void justSpinner() {
//        if (isTextViewPResent){
//            animateOutSpinnerheadAndTextve();
//        }else{
//            animateOutSpinnerheadAndSpinner();
//        }
        animateInSpinnerheadAndSpinner();
        isOtherChosen=false;
        isSpinnerPresent=true;
    }

    private void animateOutOtherchosen() {
        isOtherChosen=false;
        textve.setVisibility(View.GONE);
    }

    private void animateOutOtherchosen2() {
        isOtherChosen=false;
        animateOutSpinnerheadAndTextve();
//        animateButtonUp();
    }



    public void otherchosen() {
        isOtherChosen=true;
        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(translateAnimation);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        spinner.setVisibility(View.VISIBLE);
        textve.setVisibility(View.VISIBLE);
        spinner.startAnimation(animationSet);
        textve.startAnimation(animationSet);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) spinnerhead.getLayoutParams();
        int marginTopInDp = 60;

        float density = getResources().getDisplayMetrics().density;
        int marginTopInPixels = (int) (marginTopInDp * density);

        layoutParams.setMargins(layoutParams.leftMargin, marginTopInPixels, layoutParams.rightMargin, layoutParams.bottomMargin);
        spinnerhead.setLayoutParams(layoutParams);

        spinnerhead.setVisibility(View.GONE);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}