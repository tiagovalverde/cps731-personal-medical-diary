package com.saubantiago.personalmedicaldiary.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.AppEnums;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientProfileEditActivity extends AppCompatActivity {
    // Data
    PatientProfile patientProfile;

    // Views
    Button saveButton;
    Toast toast;
    EditText editTxtViewWeight, editTxtViewHeight, editTxtViewBloodType, editTxtViewMedication, editTxtViewAllergies,
             editTxtViewMedicalHistory, editTxtViewFamilyHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_edit);
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();

        patientProfile = this.getProfileFromIntent();
        if (patientProfile != null) {
            this.preFillForm();
        }
    }

    private PatientProfile getProfileFromIntent() {
        if (getIntent().getExtras() != null) {
            return (PatientProfile) getIntent().getSerializableExtra(PatientProfileDetailsActivity.EXTRA_DATA_PATIENT_PROFILE);
        } else {
            return null;
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    private void findAllViews() {
        // Button
        saveButton = findViewById(R.id.patientProfileSaveButton);
        // EditText
        editTxtViewWeight = findViewById(R.id.editTextWeight);
        editTxtViewHeight = findViewById(R.id.editTextHeight);
        editTxtViewBloodType = findViewById(R.id.editTextBloodType);
        editTxtViewMedication = findViewById(R.id.editTextMedication);
        editTxtViewAllergies = findViewById(R.id.editTextAllergies);
        editTxtViewMedicalHistory = findViewById(R.id.editTextMedicalHistory);
        editTxtViewFamilyHistory = findViewById(R.id.editTextFamilyHistory);
    }

    private void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PatientProfileEditActivity.this.saveChanges();
            }
        });
    }

    private void saveChanges() {

        Intent replyIntent = new Intent();
        PatientProfile updatedPatientProfile = this.getUpdatedProfile();

        if (this.patientProfile == null) {
            // First time updating PatientProfile data
            replyIntent.putExtra(PatientProfileDetailsActivity.EXTRA_DATA_ACTION, AppEnums.Actions.CREATE);
        } else {
            // Updating PatientProfile data
            updatedPatientProfile.setId(this.patientProfile.getId());
            updatedPatientProfile.setUserId(this.patientProfile.getUserId());
            replyIntent.putExtra(PatientProfileDetailsActivity.EXTRA_DATA_ACTION, AppEnums.Actions.UPDATE);
        }
        replyIntent.putExtra(PatientProfileDetailsActivity.EXTRA_DATA_PATIENT_PROFILE, updatedPatientProfile);

        // Set the result status to indicate success.
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private PatientProfile getUpdatedProfile() {
        return new PatientProfile(
                this.editTxtViewWeight.getText().toString(),
                this.editTxtViewHeight.getText().toString(),
                this.editTxtViewMedication.getText().toString(),
                this.editTxtViewAllergies.getText().toString(),
                this.editTxtViewFamilyHistory.getText().toString(),
                this.editTxtViewMedicalHistory.getText().toString(),
                this.editTxtViewBloodType.getText().toString()
        );
    }

    private void preFillForm() {
        this.editTxtViewWeight.setText(this.patientProfile.getWeight());
        this.editTxtViewHeight.setText(this.patientProfile.getHeight());
        this.editTxtViewMedication.setText(this.patientProfile.getMedication());
        this.editTxtViewAllergies.setText(this.patientProfile.getAllergies());
        this.editTxtViewFamilyHistory.setText(this.patientProfile.getFamilyHistory());
        this.editTxtViewMedicalHistory.setText(this.patientProfile.getMedicalHistory());
        this.editTxtViewBloodType.setText(this.patientProfile.getBloodType());
    }
}