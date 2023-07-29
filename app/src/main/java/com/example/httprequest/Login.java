package com.example.httprequest;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (!Constants.validaInputs(email, password, Login.this)) return;



                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toaster.show(Login.this, "Signed in successfully");
                                        startActivity(new Intent(Login.this,EmergORNotSelector.class));
                                    } else {
                                        Toaster.show(Login.this, "Login was not successful");
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
            }
        });




    }


}

