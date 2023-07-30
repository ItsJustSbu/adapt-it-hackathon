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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


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

        Button google = findViewById(R.id.gglogin);

        GoogleSignInClient signInClient;

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1065228130707-al1evc859q432cef2f6o47m4me29h1ci.apps.googleusercontent.com")
                        .requestEmail().build();

        signInClient = GoogleSignIn.getClient(Login.this,googleSignInOptions);



        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = signInClient.getSignInIntent();
                startActivityForResult(intent,100);

//                startActivity(new Intent(Login.this,EmergORNotSelector.class));

            }
        });

        btnlgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.isEmpty()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toaster.show(Login.this, "Invalid email or Password");
                        }
                    });
                }


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                Toaster.show(Login.this,"Google sign in successful");
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Check condition
                                if (task.isSuccessful()) {
                                    // When task is successful redirect to profile activity display Toast
                                    startActivity(new Intent(Login.this, EmergORNotSelector.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    Toaster.show(Login.this,"Firebase authentication successful");
                                } else {
                                    // When task is unsuccessful display Toast
                                    Toaster.show(Login.this,"Authentication Failed :" + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

