package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;

import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;

import static com.saubantiago.personalmedicaldiary.constants.Constants.EXTRA_DATA_SELF_ASSESSMENT;

public class AssessmentsViewActivity extends AppCompatActivity {

    // Views
    Button editButton;

    //Data
    Answers answers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        // initialization

        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Button
        editButton = findViewById(R.id.CreateAssessment);

    }

    private void setListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssessmentsViewActivity.this.launchAssessmentCreator();
            }
        });
    }
    private void launchAssessmentCreator() {
        Intent intent = new Intent(this, AssessmentsEditActivity.class);
        startActivity(intent);
    }

}