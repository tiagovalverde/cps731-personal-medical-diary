package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.database.view.QuestionsViewModel;
import com.saubantiago.personalmedicaldiary.database.entities.Questions;


import java.util.List;

public class AssessmentsEditActivity extends AppCompatActivity {
    // Views
    String assessmentType;
    QuestionsViewModel questionsViewModel;
    private Questions question;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessments);
        // initialization
        assessmentType = getIntent().getStringExtra("getData");
        System.out.println(assessmentType);
        this.insertQuestions();
        this.setObserver();
    }

    private void insertQuestions() {
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        Questions question1 = new Questions("Do you have a fever?");
        Questions question2 = new Questions("Do you have a cough?");
        Questions question3 = new Questions("Do you have a sore throat?");
        questionsViewModel.insert(question1);
        questionsViewModel.insert(question2);
        questionsViewModel.insert(question3);
    }

    private void preFillData(Questions question) {
        if(assessmentType.equals("COVID Assessment")){
            final TextView rowTextView = new TextView(this);
            System.out.println(question);

        }
    }

    private void setObserver() {
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        LiveData<List<Questions>> questions = questionsViewModel.getAllQuestions();



    }

}
