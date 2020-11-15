package com.saubantiago.personalmedicaldiary.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.saubantiago.personalmedicaldiary.R;

import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.activities.caregiver.CaregiverDetailsActivity;
import com.saubantiago.personalmedicaldiary.activities.medicalrecords.MedicalRecordsListActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;

public class Dashboard extends AppCompatActivity {
    // Data
    long userId;

    // Views
    Button patientProfileButton, medicalRecordsButton, assessmentsButton, caregiversButton,  logoutButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // initialization
        //this.getUserSession();
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        patientProfileButton = findViewById(R.id.PatientProfile);
        medicalRecordsButton = findViewById(R.id.MedicalRecords);
        assessmentsButton = findViewById(R.id.Assessments);
        caregiversButton = findViewById(R.id.dashboardCaregiversButton);
        logoutButton = findViewById(R.id.logoutBtn);
    }

    private void setListeners() {
        patientProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(PatientProfileDetailsActivity.class);
            }
        });
        caregiversButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(CaregiverDetailsActivity.class);
            }
        });
        medicalRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(MedicalRecordsListActivity.class);
            }
        });
        assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Assessments List activity screen missing
                // Dashboard.this.launchActivity();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void launchActivity(Class activityClass) {
        startActivity(new Intent(this, activityClass));
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
