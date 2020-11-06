package com.saubantiago.personalmedicaldiary.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.activities.login.LoginActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

public class Dashboard extends AppCompatActivity {
    // Data
    int currentUserId;

    // Views
    Button patientProfileButton, medicalRecordsButton, assessmentsButton, logoutButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // this is the user Id used to add the relationships
        this.currentUserId = this.getUserIdFromIntent();

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    /**
     * Obtains user id from logged in session provided by the login screen (splash screen later)
     */
    private int getUserIdFromIntent() {
        if (getIntent().getExtras() != null) {
            return getIntent().getIntExtra("user_id", -1);
        } else {
            return -1;
        }
    }

    private void findAllViews() {
        //Button
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


    private void launchPatientProfile() {
        Intent intent = new Intent(this, PatientProfileDetailsActivity.class);
        startActivity(intent);
    }

    private void launchMedicalRecords() {
        //Intent intent = new Intent(this, MedicalRecordsDetailsActivity.class);
        //startActivity(intent);
    }

    private void launchAssessments() {
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
