package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedicalRecordDetailsActivity extends AppCompatActivity {
    Button editButton;
    TextView txtViewCreatedAt, txtViewFileType, txtViewMedicalRecordType;

    private MedicalRecord medicalRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_details);

        // init
        this.displayBackButton();
        this.getDataFromIntent();
        this.findAllViews();
        this.setListeners();
        this.populateData();
    }

    private void findAllViews() {
        editButton = findViewById(R.id.mr_editButton);
        txtViewCreatedAt = findViewById(R.id.mr_txtCreatedAtValue);
        txtViewFileType = findViewById(R.id.mr_txtFileTypeValue);
        txtViewMedicalRecordType = findViewById(R.id.mr_txtRecordTypeValue);
    }

    private void populateData() {
        // TODO Format long date into formatted Date object
        txtViewCreatedAt.setText(Long.toString(this.medicalRecord.getCreatedAt()));
        txtViewFileType.setText(this.medicalRecord.getFileType());
        txtViewMedicalRecordType.setText(this.medicalRecord.getMedicalRecordType());
    }

    private void setListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                launchEditActivity();
            }
        });
    }

    private void loadDocument() {
        // TODO add logic to show image in screen
    }

    public void launchEditActivity() {
        Intent intent = new Intent(this, MedicalRecordEditActivity.class);
        intent.putExtra(Constants.EXTRA_DATA_MEDICAL_RECORD, this.medicalRecord);
        startActivityForResult(intent, Constants.CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            this.medicalRecord = (MedicalRecord) data.getSerializableExtra(Constants.EXTRA_DATA_MEDICAL_RECORD);
            this.populateData();
        } catch (Exception e) {
            // TODO handler error (activities nav issue)
        }
    }

    private void getDataFromIntent() {
        if(getIntent().getExtras() != null) {
            this.medicalRecord = (MedicalRecord) getIntent().getSerializableExtra(Constants.EXTRA_DATA_MEDICAL_RECORD);
        } else {
            this.medicalRecord =  null;
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}