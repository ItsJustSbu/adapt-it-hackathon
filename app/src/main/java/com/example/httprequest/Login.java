package com.example.httprequest;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    static boolean bothPass = false;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnlgin = findViewById(R.id.lginbtn);
        EditText Email = findViewById(R.id.email);
        EditText Password = findViewById(R.id.pass);

        TextView notsigned = findViewById(R.id.tvsign);

        btnlgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = Email.getText().toString().replace(" ", "");
                String password = Password.getText().toString().replace(" ", "");

                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toaster.show(Login.this, "Login was a successful");
                                }else{
                                    Toaster.show(Login.this, "Login was a failure");
                                }
                            }
                        });




            }
        });

        notsigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(Login.this, R.anim.bounce);
                notsigned.startAnimation(animation);
                startActivity(new Intent(Login.this, RegisterActivity.class));
//                Constants.animateCuteButton(notsigned, new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animator) {
//                        // Animation start callback
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animator) {
//                        // Animation end callback
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animator) {
//                        // Animation cancel callback
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animator) {
//                        // Animation repeat callback
//                    }
//                });
            }
        });




    }


}

