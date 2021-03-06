package com.saubantiago.personalmedicaldiary.activities.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.AppEnums;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.caregiver.CaregiverDetailsActivity;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.view.PatientProfileViewModel;

import java.util.List;

public class PatientProfileDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_PATIENT_PROFILE = "extra_word_patient_profile";
    public static final String EXTRA_DATA_ACTION = "extra_word_action";
    public static final int CREATE_UPDATE_ACTIVITY_REQUEST_CODE = 1;

    // Views
    TextView txtViewWeight, txtViewHeight, txtViewAllergies, txtViewMedication, txtVIewBloodType, txtViewMedicalHistory, txtViewFamilyHistory;
    Button editButton, caregiverButton;

    // live data
    PatientProfileViewModel patientProfileViewModel;
    private List<PatientProfile> patientProfiles;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_details);

        // initialization
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.findAllViews();
        this.setListeners();
        this.displayBackButton();
        this.setObserver();
    }

    private void setObserver() {
        patientProfileViewModel = ViewModelProviders.of(this).get(PatientProfileViewModel.class);
        patientProfileViewModel.getAllPatientProfilesByUserUID(user.getUid()).observe(this, new Observer<List<PatientProfile>>() {
            @Override
            public void onChanged(List<PatientProfile> patientProfiles) {
                PatientProfileDetailsActivity.this.patientProfiles = patientProfiles;
                PatientProfileDetailsActivity.this.preFillData();
            }
        });
    }

    private void findAllViews() {
        // Views
        txtViewWeight = findViewById(R.id.txtViewWeightValue);
        txtViewHeight = findViewById(R.id.txtViewHeightValue);
        txtViewAllergies = findViewById(R.id.txtViewAllergiesValue);
        txtViewMedication = findViewById(R.id.txtViewMedicationValue);
        txtVIewBloodType = findViewById(R.id.txtViewBloodTypeValue);
        txtViewMedicalHistory = findViewById(R.id.txtViewMedicalHistoryValue);
        txtViewFamilyHistory = findViewById(R.id.txtViewFamilyHistoryValue);
        // Buttons
        editButton = findViewById(R.id.editPatientProfileButton);
        caregiverButton = findViewById(R.id.caregiverButton);
    }

    private void setListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PatientProfileDetailsActivity.this.launchEditActivity();
            }
        });

        caregiverButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PatientProfileDetailsActivity.this.launchCaregiverDetailsActivity();
            }
        });
    }

    private void preFillData() {
        if(this.patientProfileExists()) {
            PatientProfile patientProfile = this.patientProfiles.get(0);
            this.txtViewWeight.setText(patientProfile.getWeight());
            this.txtViewHeight.setText(patientProfile.getHeight());
            this.txtViewMedication.setText(patientProfile.getMedication());
            this.txtViewAllergies.setText(patientProfile.getAllergies());
            this.txtViewFamilyHistory.setText(patientProfile.getFamilyHistory());
            this.txtViewMedicalHistory.setText(patientProfile.getMedicalHistory());
            this.txtVIewBloodType.setText(patientProfile.getBloodType());
        }
    }

    private boolean patientProfileExists() {
        return this.patientProfiles != null && !this.patientProfiles.isEmpty();
    }

    public void launchEditActivity() {
        Intent intent = new Intent(this, PatientProfileEditActivity.class);
        if (this.patientProfileExists()) {
            PatientProfile patientProfile = this.patientProfiles.get(0);
            intent.putExtra(EXTRA_DATA_PATIENT_PROFILE, patientProfile);
        }
        startActivityForResult(intent, CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void launchCaregiverDetailsActivity() {
        startActivity(new Intent(this, CaregiverDetailsActivity.class));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PatientProfile updatedPatientProfile = (PatientProfile) data.getSerializableExtra(EXTRA_DATA_PATIENT_PROFILE);
        updatedPatientProfile.setUserUID(user.getUid());
        AppEnums.Actions actionType = (AppEnums.Actions) data.getSerializableExtra(EXTRA_DATA_ACTION);

        if(actionType == AppEnums.Actions.CREATE) {
            patientProfileViewModel.insert(updatedPatientProfile);
        }
        else if(actionType == AppEnums.Actions.UPDATE) {
            patientProfileViewModel.update(updatedPatientProfile);
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}