package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;

import com.saubantiago.personalmedicaldiary.activities.medicalrecords.MedicalRecordEditActivity;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;
import com.saubantiago.personalmedicaldiary.database.view.SelfAssessmentsViewModel;

import static com.saubantiago.personalmedicaldiary.constants.Constants.EXTRA_DATA_SELF_ASSESSMENT;

public class AssessmentsViewActivity extends AppCompatActivity {

    // Views
    Button editButton, deleteButton;
    TextView assessmentTypeTextView;

    //Data
    Answers answers;
    SelfAssessmentsViewModel sfViewModel;
    private SelfAssessments selfAssessment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assessments);

        // initialization
        sfViewModel = ViewModelProviders.of(this).get(SelfAssessmentsViewModel.class);
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.populateData();
    }

    private void populateData() {
        assessmentTypeTextView.setText(selfAssessment.getAssessmentType());

    }

    private void findAllViews() {
        //Button
        editButton = findViewById(R.id.sf_editAssessment);
        deleteButton = findViewById(R.id.sf_deleteButton);

        //TextView
        assessmentTypeTextView = findViewById(R.id.sf_AssessmentTypeData);
    }

    private void setListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                launchEditActivity();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSelfAssessment();
            }
        });
    }

    private void launchEditActivity() {
        Intent intent = new Intent(this, AssessmentsEditActivity.class);
        intent.putExtra(EXTRA_DATA_SELF_ASSESSMENT, this.selfAssessment);
        startActivityForResult(intent, Constants.CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    private void deleteSelfAssessment() {
        sfViewModel.delete(selfAssessment);
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            this.selfAssessment = (SelfAssessments) data.getSerializableExtra(Constants.EXTRA_DATA_SELF_ASSESSMENT);
            this.populateData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

}