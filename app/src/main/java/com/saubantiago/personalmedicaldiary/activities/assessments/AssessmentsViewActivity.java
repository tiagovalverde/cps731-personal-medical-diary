package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;

import com.saubantiago.personalmedicaldiary.activities.medicalrecords.MedicalRecordEditActivity;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.view.AnswersViewModel;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;
import com.saubantiago.personalmedicaldiary.database.view.SelfAssessmentsViewModel;

import java.util.List;

import static com.saubantiago.personalmedicaldiary.constants.Constants.EXTRA_DATA_ANSWER;
import static com.saubantiago.personalmedicaldiary.constants.Constants.EXTRA_DATA_SELF_ASSESSMENT;

public class AssessmentsViewActivity extends AppCompatActivity {

    // Views
    Button editButton, deleteButton;
    TextView assessmentTypeTextView, question1TextView, question2TextView, question3TextView, answer1TextView, answer2TextView, answer3TextView;

    //Data
    AnswersViewModel answersViewModel;
    SelfAssessmentsViewModel sfViewModel;
    private SelfAssessments selfAssessment;
    private List<Answers> answersList;
    Long id;
    Answers answer1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assessments);

        // initialization
        sfViewModel = ViewModelProviders.of(this).get(SelfAssessmentsViewModel.class);
        this.displayBackButton();
        this.getDataFromIntent();
        this.findAllViews();
        this.getData();
        this.setListeners();
    }

    private void getData() {
        assessmentTypeTextView.setText(selfAssessment.getAssessmentType());

        id = selfAssessment.getId();

        answersViewModel = ViewModelProviders.of(this).get(AnswersViewModel.class);
        answersViewModel.getAnswersByAssessmentID(id).observe(this, new Observer<List<Answers>>() {
            @Override
            public void onChanged(List<Answers> _answers){
                answersList = _answers;
                populateData();
            }
        });

    }

    private void populateData(){
        question1TextView.setText(answersList.get(0).getQuestionID());
        answer1TextView.setText(answersList.get(0).getAnswer());

        question2TextView.setText(answersList.get(1).getQuestionID());
        answer2TextView.setText(answersList.get(1).getAnswer());

        question3TextView.setText(answersList.get(2).getQuestionID());
        answer3TextView.setText(answersList.get(2).getAnswer());

        /**
        for(Answers answer : answersList){
            ConstraintLayout constraintLayout = findViewById(R.id.sf_constraintLayout);

            TextView qView = new TextView(this);
            TextView aView = new TextView(this);

            int x = 100;
            int y = 100;

            qView.setText(answer.getQuestionID());
            aView.setText(answer.getAnswer());

            ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);

            newParams.width = x;
            newParams.height = y;

            newParams.editorAbsoluteX = ((int) qView.getX());

            aView.setLayoutParams(newParams);

            qView.setPadding(20, 20, 20, 20);
            aView.setPadding(20, 20, 20, 20);

            constraintLayout.addView(qView, newParams);
            constraintLayout.addView(aView, newParams);
        }
         **/
    }

    private void findAllViews() {
        //Button
        editButton = findViewById(R.id.sf_editAssessment);
        deleteButton = findViewById(R.id.sf_deleteButton);

        //TextView

        assessmentTypeTextView = findViewById(R.id.sf_AssessmentTypeData);

        question1TextView = findViewById(R.id.sf_question1TextView);
        question2TextView = findViewById(R.id.sf_question2TextView);
        question3TextView = findViewById(R.id.sf_question3TextView);
        answer1TextView = findViewById(R.id.sf_Answer1TextView);
        answer2TextView = findViewById(R.id.sf_Answer2TextView);
        answer3TextView = findViewById(R.id.sf_Answer3TextView);

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
        //TODO delete answer records as well

        //answersViewModel.delete(answersList.get(0));
        //answersViewModel.delete(answersList.get(1));
        //answersViewModel.delete(answersList.get(2));
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            this.selfAssessment = (SelfAssessments) data.getSerializableExtra(EXTRA_DATA_SELF_ASSESSMENT);
            this.populateData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }


    private void getDataFromIntent() {
        if(getIntent().getExtras() != null) {
            this.selfAssessment = (SelfAssessments) getIntent().getSerializableExtra(EXTRA_DATA_SELF_ASSESSMENT);
            assert selfAssessment != null;
            System.out.println(selfAssessment.toString());
        } else {
            this.selfAssessment =  null;
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

}