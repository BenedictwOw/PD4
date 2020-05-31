package com.myapplicationdev.android.pd4.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.myapplicationdev.android.pd4.myapplication.R;

public class homepage extends AppCompatActivity {
    ImageView btnAttendance,btnProfile,btnPitchPipe;
    TextView btnLogout;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnAttendance = (ImageView)findViewById(R.id.btnAttendance);
        btnProfile = (ImageView)findViewById(R.id.btnProfile);
        btnLogout  = (TextView)findViewById(R.id.btnLogout);
        btnPitchPipe = (ImageView)findViewById(R.id.btnPitchPipe);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Profile.class));
            }
        });
        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), attendance.class);
                startActivity(intent2);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(homepage.this);
                myBuilder.setTitle("Would you like to log out?");
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout(v);
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(homepage.this,"Your have logged out!",Toast.LENGTH_SHORT).show();
                    }
                });
                //Configuring the 'negative' button
                myBuilder.setNeutralButton("No", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
        btnPitchPipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PitchPipe.class);
                startActivity(intent);
            }
        });
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
