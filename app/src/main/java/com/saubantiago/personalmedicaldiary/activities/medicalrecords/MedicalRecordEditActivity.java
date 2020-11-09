package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MedicalRecordEditActivity extends AppCompatActivity {
    MedicalRecord medicalRecord;
    User user;
    Button saveButton;
    EditText editTextFileType, editTextMedicalRecordType;
    UserViewModal userViewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_edit);

        // init
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.userViewModal = ViewModelProviders.of(this).get(UserViewModal .class);
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
            this.user = (User) getIntent().getSerializableExtra(Constants.EXTRA_DATA_USER);
        } else {
            this.medicalRecord =  null;
            this.user =  null;
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
                MedicalRecordEditActivity.this.saveChanges();
            }
        });
    }

    public void saveChanges() {
        String fileType = editTextFileType.getText().toString();
        String medicalRecordType = editTextMedicalRecordType.getText().toString();
        Intent replyIntent = new Intent();

        if (this.medicalRecord != null) {
            this.medicalRecord.setFileType(fileType);
            this.medicalRecord.setMedicalRecordType(medicalRecordType);
            this.userViewModal.updateMedicalRecord(user, medicalRecord);
            replyIntent.putExtra(Constants.EXTRA_DATA_MEDICAL_RECORD, this.medicalRecord);

        } else {
            MedicalRecord newMedicalRecord = new MedicalRecord(fileType, medicalRecordType);
            this.userViewModal.insertMedicalRecord(user, newMedicalRecord);
        }

        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}