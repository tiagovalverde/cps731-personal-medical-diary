package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicalRecordDetailsActivity extends AppCompatActivity {
    Button editButton, deleteButton;
    TextView txtViewCreatedAt, txtViewFileType, txtViewMedicalRecordType;
    ImageView imageView;

    private MedicalRecord medicalRecord;
    MedicalRecordViewModel medicalRecordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_details);

        // init
        medicalRecordViewModel = ViewModelProviders.of(this).get(MedicalRecordViewModel.class);
        this.displayBackButton();
        this.getDataFromIntent();
        this.findAllViews();
        this.setListeners();
        this.populateData();
    }

    private void findAllViews() {
        editButton = findViewById(R.id.mr_editButton);
        deleteButton = findViewById(R.id.mr_deleteButton);
        txtViewCreatedAt = findViewById(R.id.mr_txtCreatedAtValue);
        txtViewFileType = findViewById(R.id.mr_txtFileTypeValue);
        txtViewMedicalRecordType = findViewById(R.id.mr_txtRecordTypeValue);
        imageView = findViewById(R.id.mr_details_imageView);
    }

    private void populateData() {
        long created_at = medicalRecord.getCreatedAt();
        txtViewCreatedAt.setText(Utils.formattedDateTime(created_at, Utils.DATE_FORMAT));
        txtViewFileType.setText(medicalRecord.getFileType());
        txtViewMedicalRecordType.setText(medicalRecord.getMedicalRecordType());

        Bitmap bm = BitmapFactory.decodeFile(medicalRecord.getFileLocation());
        imageView.setImageBitmap(bm);
        imageView.setVisibility(View.VISIBLE);
    }

    private void setListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                launchEditActivity();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMedicalRecord();
            }
        });
    }

    private void deleteMedicalRecord() {
        medicalRecordViewModel.delete(medicalRecord);
        finish();
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
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
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