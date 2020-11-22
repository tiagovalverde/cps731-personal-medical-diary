package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.view.AnswersViewModel;
import com.saubantiago.personalmedicaldiary.database.view.QuestionsViewModel;
import com.saubantiago.personalmedicaldiary.database.entities.Questions;


import java.util.ArrayList;
import java.util.List;

import static com.saubantiago.personalmedicaldiary.constants.Constants.EXTRA_DATA_SELF_ASSESSMENT;

public class AssessmentsEditActivity extends AppCompatActivity {
    // Views
    String assessmentType;
    QuestionsViewModel questionsViewModel;
    Button saveButton;
    List<TextView> allTvs;

    // live data
    private List<Questions> questions;
    AnswersViewModel answersViewModel;
    Answers answers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessments);
        // initialization
        assessmentType = getIntent().getStringExtra(EXTRA_DATA_SELF_ASSESSMENT);

        this.displayBackButton();
        this.setObserver();
    }


    private void setObserver() {
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        questionsViewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> _questions) {
                questions = _questions;
                postQuestions();
            }
        });
    }

    private void postQuestions() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.assessmentLL);
        saveButton = new Button(this);
        allTvs = new ArrayList<TextView>();
        String save = "Save";

        for(int i = 0; i < questions.size(); i++){

            TextView textView = new TextView(this);
            EditText editText = new EditText(this);

            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(questions.get(i).getQuestion());

            allTvs.add(textView);

            textView.setPadding(20, 20, 20, 20);
            editText.setId(ViewCompat.generateViewId());

            linearLayout.addView(textView);
            linearLayout.addView(editText);

        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        saveButton.setText(save);
        linearLayout.addView(saveButton, params);

        setListeners();
    }

    private void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssessmentsEditActivity.this.saveAssessment();
            }
        });
    }

    private void saveAssessment() {
        answersViewModel = ViewModelProviders.of(this).get(AnswersViewModel.class);

        for(int i = 1; i < questions.size()+1; i++){
            EditText ed = findViewById(i);

            answers = new Answers(ed.getText().toString(), assessmentType, allTvs.get(i-1).getText().toString());
            answersViewModel.insert(answers);
        }
        returnToAssessmentDetails();
    }
    private void returnToAssessmentDetails() {
        Intent intent = new Intent(this, AssessmentsDetailsActivity.class);
        startActivity(intent);
    }


    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

}
