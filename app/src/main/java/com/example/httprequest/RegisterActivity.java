package com.example.httprequest;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    static OkHttpClient client = new OkHttpClient();
    static OkHttpClient client2 = new OkHttpClient();
    static String responseStr;
    static String typee;

    static String username;
    static String email;
    static String password;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        typee = "Parent";
        auth = FirebaseAuth.getInstance();

        Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.bounce);

        LinearLayout parentSelected = findViewById(R.id.parentSelected);
        LinearLayout childSelected = findViewById(R.id.childSelected);

        Button btnRegister = findViewById(R.id.btnRegister);

        parentSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentSelected.startAnimation(animation);
                parentSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                childSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                typee="Parent";
                btnRegister.setText("Create account");
            }
        });

        childSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childSelected.startAnimation(animation);
                parentSelected.setBackground( getResources().getDrawable(R.drawable.parchi_notselected));
                childSelected.setBackground( getResources().getDrawable(R.drawable.parchi_selected));
                typee="Child";
                btnRegister.setText("Create account");
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

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Registration was a success", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(RegisterActivity.this, "Registration was a failure", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

//    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//    Call post(String url, String json, Callback callback) {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//        return call;
//    }



}