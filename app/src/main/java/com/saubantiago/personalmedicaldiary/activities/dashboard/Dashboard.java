package com.saubantiago.personalmedicaldiary.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.assessments.AssessmentsDetailsActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;

public class Dashboard extends AppCompatActivity {
    // Views
    Button patientProfileButton, medicalRecordsButton, assessmentsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Button
        patientProfileButton = findViewById(R.id.PatientProfile);
        medicalRecordsButton = findViewById(R.id.MedicalRecords);
        assessmentsButton = findViewById(R.id.Assessments);

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
        Intent intent = new Intent(this, AssessmentsDetailsActivity.class);
        startActivity(intent);
    }

}
