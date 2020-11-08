package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;

public class ChooseAssessmentsActivity extends AppCompatActivity {
    // Views
    Button createChosenAssessmentButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_assessments);

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Button
        createChosenAssessmentButton = findViewById(R.id.CreateChosenAssessment);

    }

    private void setListeners() {
        createChosenAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseAssessmentsActivity.this.launchAssessmentQuestionnaire();
            }
        });
    }
    private void launchAssessmentQuestionnaire() {
        //Intent intent = new Intent(this, ChooseAssessmentsActivity.class);
        //startActivity(intent);
    }
}

