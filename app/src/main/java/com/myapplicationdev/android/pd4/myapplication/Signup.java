package com.myapplicationdev.android.pd4.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.myapplicationdev.android.pd4.myapplication.R;

public class Signup extends AppCompatActivity {
    public static final String EXTRA_EMAIL = "com.myapplicationdev.android.pd4.EXTRA_EMAIL";
    public static final String EXTRA_PASS = "com.myapplicationdev.android.pd4.EXTRA_PASS";
    private static final String KEY_USER = "studentID";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NUMBER = "number";
    private static final String TAG = "TAG";

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    EditText etName, etStudentID, etEmail, etNumber, etPassword;
    Button btnSignUp;
    ProgressBar progressBar;
    String name,email,number,userID,password,id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etNumber = (EditText)findViewById(R.id.etNumber);
        etStudentID = (EditText)findViewById(R.id.etStudentID);
        etPassword = (EditText)findViewById(R.id.etPassowrd);
        etStudentID = (EditText)findViewById(R.id.etStudentID);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        progressBar.setVisibility(View.INVISIBLE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = etStudentID.getText().toString().trim();
                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                number = etNumber.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    etEmail.setError("Email is required");
                    return;
                }if(TextUtils.isEmpty(password)){
                    etPassword.setError("Password is required");
                    return;
                }if(password.length() < 6){
                    etPassword.setError("Password must be > 6 characters");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    etName.setError("Name is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //register user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this,"User created",Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object>user = new HashMap<>();
                            user.put(KEY_USER,id);
                            user.put(KEY_NAME,name);
                            user.put(KEY_EMAIL,email);
                            user.put(KEY_NUMBER,number);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: profile is" +userID);
                                }
                            });
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            intent.putExtra(EXTRA_EMAIL,email);
                            intent.putExtra(EXTRA_PASS,password);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Signup.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

}
