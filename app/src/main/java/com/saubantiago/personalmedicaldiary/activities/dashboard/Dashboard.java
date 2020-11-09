package com.saubantiago.personalmedicaldiary.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.activities.medicalrecords.MedicalRecordsActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;

public class Dashboard extends AppCompatActivity {
    // Data
    long userId;

    // Views
    Button patientProfileButton, medicalRecordsButton, assessmentsButton, logoutButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // initialization
        this.getUserSession();
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        patientProfileButton = findViewById(R.id.PatientProfile);
        medicalRecordsButton = findViewById(R.id.MedicalRecords);
        assessmentsButton = findViewById(R.id.Assessments);
        logoutButton = findViewById(R.id.logoutBtn);
    }

    private void setListeners() {
        patientProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.launchPatientProfile();
            }
        });
        medicalRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.launchMedicalRecords();
            }
        });
        assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.launchAssessments();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.logout();
            }
        });
    }

    private void getUserSession() {
        if (SessionManager.getLoggedInUserStatus(this)) {
            this.userId = SessionManager.getLoggedInUserId(this);
        } else {
            finish();
        }
    }

    private void launchPatientProfile() {
        Intent intent = new Intent(this, PatientProfileDetailsActivity.class);
        startActivity(intent);
    }

    private void launchMedicalRecords() {
        Intent intent = new Intent(this, MedicalRecordsActivity.class);
        startActivity(intent);
    }

    private void launchAssessments() {
        // TODO uncomment when assessments implemented
        //Intent intent = new Intent(this, AssessmentsDetailsActivity.class);
        //startActivity(intent);
    }

    private void logout() {
        SessionManager.clearLoggedInUserSession(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
