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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.view.AnswersViewModel;
import com.saubantiago.personalmedicaldiary.database.view.QuestionsViewModel;
import com.saubantiago.personalmedicaldiary.database.entities.Questions;
import com.saubantiago.personalmedicaldiary.database.view.SelfAssessmentsViewModel;


import java.util.ArrayList;
import java.util.List;

import static com.saubantiago.personalmedicaldiary.constants.Constants.EXTRA_DATA_SELF_ASSESSMENT;

public class AssessmentsEditActivity extends AppCompatActivity {
    // Views
    String assessmentType;
    Button saveButton;
    TextView textViewQuestion1, textViewQuestion2, textViewQuestion3, assessmentTypeTextView;
    EditText editTextAnswer1, editTextAnswer2, editTextAnswer3;
    //List<TextView> allTvs;

    // live data
    FirebaseUser user;
    private List<Questions> questions;
    AnswersViewModel answersViewModel;
    QuestionsViewModel questionsViewModel;
    SelfAssessments sf;
    SelfAssessmentsViewModel sfViewModel;
    Answers answer1;
    Answers answer2;
    Answers answer3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessments);

        // initialization
        user = FirebaseAuth.getInstance().getCurrentUser();

        assessmentType = getIntent().getStringExtra(EXTRA_DATA_SELF_ASSESSMENT);

        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        sfViewModel = ViewModelProviders.of(this).get(SelfAssessmentsViewModel.class);
        answersViewModel = ViewModelProviders.of(this).get(AnswersViewModel.class);

        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.setObserver();
    }


    private void findAllViews() {
        assessmentTypeTextView = findViewById(R.id.assessmentTypeTextView);
        assessmentTypeTextView.setText(assessmentType);
        saveButton = findViewById(R.id.saveAssessmentButton);
        textViewQuestion1 = findViewById(R.id.textViewQuestion1);
        textViewQuestion2 = findViewById(R.id.textViewQuestion2);
        textViewQuestion3 = findViewById(R.id.textViewQuestion3);
        editTextAnswer1 = findViewById(R.id.editTextAnswer1);
        editTextAnswer2 = findViewById(R.id.editTextAnswer2);;
        editTextAnswer3 = findViewById(R.id.editTextAnswer3);
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
        answer1 = new Answers(editTextAnswer1.getText().toString(), assessmentType, textViewQuestion1.getText().toString());
        answer2 = new Answers(editTextAnswer2.getText().toString(), assessmentType, textViewQuestion2.getText().toString());
        answer3 = new Answers(editTextAnswer3.getText().toString(), assessmentType, textViewQuestion3.getText().toString());

        answersViewModel.insert(answer1);
        answersViewModel.insert(answer2);
        answersViewModel.insert(answer3);

        SelfAssessments sf = new SelfAssessments(assessmentType, user.getUid());
        System.out.println(sf.toString());
        sfViewModel.insert(sf);

        /**
         for(int i = 1; i < questions.size()+1; i++){
         EditText ed = findViewById(i);

         answers = new Answers(ed.getText().toString(), assessmentType, allTvs.get(i-1).getText().toString());
         answersViewModel.insert(answers);
         }
         **/

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
        textViewQuestion1.setText(questions.get(0).getQuestion());
        textViewQuestion2.setText(questions.get(1).getQuestion());
        textViewQuestion3.setText(questions.get(2).getQuestion());

        /**
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
        **/

    }
}
