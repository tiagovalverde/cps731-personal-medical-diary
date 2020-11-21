package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;

public class AssessmentsDetailsActivity extends AppCompatActivity {
    // Views
    Button createAssessmentButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Button
        createAssessmentButton = findViewById(R.id.CreateAssessment);

    }

    private void setListeners() {
        createAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssessmentsDetailsActivity.this.launchAssessmentCreator();
            }
        });
    }
    private void launchAssessmentCreator() {
        Intent intent = new Intent(this, ChooseAssessmentsActivity.class);
        startActivity(intent);
    }

}
