package com.example.httprequest;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    static String typee;
    static String username;
    static String email;
    static String password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        typee = "Adult";
        auth = FirebaseAuth.getInstance();

        Button google = findViewById(R.id.ggreg);

        GoogleSignInClient signInClient;

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1065228130707-al1evc859q432cef2f6o47m4me29h1ci.apps.googleusercontent.com")
                .requestEmail().build();

        signInClient = GoogleSignIn.getClient(RegisterActivity.this,googleSignInOptions);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = signInClient.getSignInIntent();
                startActivityForResult(intent,100);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.bounce);

        LinearLayout adultSelected = findViewById(R.id.adultSelected);
        LinearLayout childSelected = findViewById(R.id.childSelected);

        Button btnRegister = findViewById(R.id.btnRegister);

        adultSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adultSelected.startAnimation(animation);
                adultSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                childSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                typee="Adult";
                btnRegister.setText("Next");
            }
        });

        childSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childSelected.startAnimation(animation);
                adultSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                childSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                typee="Child";
                btnRegister.setText("Next");
            }
        });

        ImageView btnBack = findViewById(R.id.backreg);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        EditText Username = findViewById(R.id.Username);
        EditText Email = findViewById(R.id.Email);
        EditText Password = findViewById(R.id.Password);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = Username.getText().toString();
                email = Email.getText().toString().replace(" ","");
                password = Password.getText().toString().replace(" ","");

                if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.isEmpty()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toaster.show(RegisterActivity.this, "Invalid email or Password");
                        }
                    });
                } else {

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toaster.show(RegisterActivity.this, "Registration was a success");
                                        Intent intent = new Intent(RegisterActivity.this, hidden1.class);
                                        startActivity(intent);

                                    } else {
                                        Toaster.show(RegisterActivity.this, "Registration was a failure");
                                    }
                                }
                            });
                }
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
                String s = "Google sign in successful";
                // Display Toast
                displayToast(s);
                // Initialize sign in account
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
                                    startActivity(new Intent(RegisterActivity.this, EmergORNotSelector.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    displayToast("Firebase authentication successful");
                                } else {
                                    // When task is unsuccessful display Toast
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
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
    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }


}