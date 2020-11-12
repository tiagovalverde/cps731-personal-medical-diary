package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MedicalRecordEditActivity extends AppCompatActivity {
    // Views
    Button saveButton;
    EditText editTextFileType, editTextMedicalRecordType;

    // Data
    FirebaseUser user;
    MedicalRecord medicalRecord;
    MedicalRecordViewModel medicalRecordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_edit);

        // init
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.medicalRecordViewModel = ViewModelProviders.of(this).get(MedicalRecordViewModel .class);
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.getMedicalRecordFromIntent();
        if (this.medicalRecord != null) {
            this.preFillForm();
        }
    }

    private void preFillForm() {
        editTextFileType.setText(this.medicalRecord.getFileType());
        editTextMedicalRecordType.setText(this.medicalRecord.getMedicalRecordType());
    }

    private void getMedicalRecordFromIntent() {
        if (getIntent().getExtras() != null) {
            this.medicalRecord =  (MedicalRecord) getIntent().getSerializableExtra(Constants.EXTRA_DATA_MEDICAL_RECORD);
        } else {
            this.medicalRecord =  null;
        }
    }

    private void findAllViews() {
        saveButton = findViewById(R.id.mr_saveButton);
        editTextFileType = findViewById(R.id.mr_editTxtFileType);
        editTextMedicalRecordType = findViewById(R.id.mr_editTxtType);
    }

    private void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveChanges();
            }
        });
    }

    public void saveChanges() {
        String fileType = editTextFileType.getText().toString();
        String medicalRecordType = editTextMedicalRecordType.getText().toString();
        Intent replyIntent = new Intent();

        if (this.medicalRecord != null) {
            // TODO add MedicalRecord remaining fields (correct data-types) when adding img upload
            medicalRecord.setFileType(fileType);
            medicalRecord.setMedicalRecordType(medicalRecordType);
            medicalRecord.setUserUID(user.getUid());
            medicalRecordViewModel.update(medicalRecord);
            replyIntent.putExtra(Constants.EXTRA_DATA_MEDICAL_RECORD, this.medicalRecord);
        } else {
            MedicalRecord newMedicalRecord = new MedicalRecord(fileType, medicalRecordType);
            newMedicalRecord.setUserUID(user.getUid());
            medicalRecordViewModel.insert(newMedicalRecord);
        }

        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}