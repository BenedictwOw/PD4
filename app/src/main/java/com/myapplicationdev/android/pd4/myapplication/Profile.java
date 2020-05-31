package com.myapplicationdev.android.pd4.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.myapplicationdev.android.pd4.myapplication.R;

public class Profile extends AppCompatActivity {
    TextView tvStudentID,tvName,tvEmail,tvPhone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvStudentID = (TextView) findViewById(R.id.profID);
        tvName = (TextView) findViewById(R.id.profName);
        tvEmail = (TextView) findViewById(R.id.profEmail);
        tvPhone = (TextView) findViewById(R.id.profHP);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                tvStudentID.setText("Student ID: " + documentSnapshot.getString("studentID"));
                tvName.setText("Name: " + documentSnapshot.getString("name"));
                tvEmail.setText("Number: " + documentSnapshot.getString("email"));
                tvPhone.setText("Contact: " + documentSnapshot.getString("number"));
            }
        });
    }
}
