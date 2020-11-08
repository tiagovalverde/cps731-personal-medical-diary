package com.saubantiago.personalmedicaldiary.activities.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saubantiago.personalmedicaldiary.AppEnums;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.activities.caregiver.CaregiverDetailsActivity;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndPatientProfile;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import java.util.List;

public class PatientProfileDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_PATIENT_PROFILE = "extra_word_patient_profile";
    public static final String EXTRA_DATA_ACTION = "extra_word_action";
    public static final int CREATE_UPDATE_ACTIVITY_REQUEST_CODE = 1;

    long userId;

    // Views
    TextView txtViewWeight, txtViewHeight, txtViewAllergies, txtViewMedication, txtVIewBloodType, txtViewMedicalHistory, txtViewFamilyHistory;
    Button editButton, caregiverButton;

    // live data
    UserViewModal userViewModal;
    private UserAndPatientProfile userAndProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_details);

        // initialization
        this.getUserSession();
        this.findAllViews();
        this.setListeners();
        this.displayBackButton();
        this.setObserver();
    }

    private void getUserSession() {
        if (SessionManager.getLoggedInUserStatus(this)) {
            this.userId = SessionManager.getLoggedInUserId(this);
        } else {
            this.launchLoginActivity();
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void setObserver() {
        userViewModal = ViewModelProviders.of(this).get(UserViewModal.class);
        userViewModal.getAllUsersAndProfile().observe(this, new Observer<List<UserAndPatientProfile>>() {
            @Override
            public void onChanged(List<UserAndPatientProfile> usersAndPatientProfile) {
                for(UserAndPatientProfile userAndProfile : usersAndPatientProfile) {
                    if(userAndProfile.user.getId() == PatientProfileDetailsActivity.this.userId) {
                        PatientProfileDetailsActivity.this.userAndProfile = userAndProfile;
                        PatientProfileDetailsActivity.this.preFillData();
                        return;
                    }
                }
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
            PatientProfile patientProfile = this.userAndProfile.patientProfiles.get(0);
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
        return this.userAndProfile != null && !this.userAndProfile.patientProfiles.isEmpty();
    }

    public void launchEditActivity() {
        Intent intent = new Intent(this, PatientProfileEditActivity.class);
        if (this.patientProfileExists()) {
            PatientProfile patientProfile = this.userAndProfile.patientProfiles.get(0);
            intent.putExtra(EXTRA_DATA_PATIENT_PROFILE, patientProfile);
        }
        startActivityForResult(intent, CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void launchCaregiverDetailsActivity() {
        Intent intent = new Intent(this, CaregiverDetailsActivity.class);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PatientProfile updatedPatientProfile = (PatientProfile) data.getSerializableExtra(EXTRA_DATA_PATIENT_PROFILE);
        AppEnums.Actions actionType = (AppEnums.Actions) data.getSerializableExtra(EXTRA_DATA_ACTION);

        if(actionType == AppEnums.Actions.CREATE) {
            this.userViewModal.insertProfile(this.userAndProfile.user, updatedPatientProfile);
        }
        else if(actionType == AppEnums.Actions.UPDATE) {
            this.userViewModal.updateProfile(this.userAndProfile.user, updatedPatientProfile);
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}