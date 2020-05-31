package com.myapplicationdev.android.pd4.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class attendance extends AppCompatActivity {
	private static final String KEY_ATTENDANCE = "Attendance";

	FirebaseFirestore fStore;
	FirebaseAuth fAuth;
	Button btnTakeAttendance;
	TextView etDate;
	RadioGroup rg;
	RadioButton rb;
	String strDate, attend,userID,id;
	DocumentReference documentReference2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_attendance);
		super.onCreate(savedInstanceState);
		fAuth = FirebaseAuth.getInstance();
		fStore = FirebaseFirestore.getInstance();
		userID = fAuth.getCurrentUser().getUid();
		btnTakeAttendance = (Button) findViewById(R.id.btnTakeAttendance);
		etDate = (TextView) findViewById(R.id.etDate);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		strDate = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
		etDate.setText(strDate);


		id = fAuth.getCurrentUser().getUid();
		documentReference2 = fStore.collection("users").document(id);
		documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
			@Override
			public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
				userID= documentSnapshot.getString("studentID");
			}
		});

		btnTakeAttendance.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String ,String> attendance = new HashMap<>();
				int selectedButtonId = rg.getCheckedRadioButtonId();
				rb = (RadioButton) findViewById(selectedButtonId);
				attend = rb.getText().toString().trim();
				attendance.put(KEY_ATTENDANCE,attend);
				DocumentReference documentReference = fStore.collection(strDate).document(userID);
				documentReference.set(attendance);
			}
		});
	}
}

