package com.myapplicationdev.android.pd4.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String textEmail,textPass,textID;
    TextView btnSignup, btnLogin;
    EditText etName,etPass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        textEmail = intent.getStringExtra(Signup.EXTRA_EMAIL);
        textPass =  intent.getStringExtra(Signup.EXTRA_PASS);
        btnSignup = (TextView)findViewById(R.id.btnSignUp1);
        etName = (EditText)findViewById(R.id.etEmailSignIn);
        etPass = (EditText)findViewById(R.id.etPassSingIn);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        btnLogin = (TextView)findViewById(R.id.btnSubmit);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);
        btnSignup.setPaintFlags(btnSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        etName.setText(textEmail);
        etPass.setText(textPass);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etName.getText().toString().trim();
                String password = etPass.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    etName.setError("Email is required");
                    return;
                }if(TextUtils.isEmpty(password)){
                    etPass.setError("Password is required");
                    return;
                }if(password.length() < 6){
                    etPass.setError("Password must be > 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), homepage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),homepage.class));
                    }
                });
            }
        });

    }
}
