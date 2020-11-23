package com.saubantiago.personalmedicaldiary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;
import com.saubantiago.personalmedicaldiary.database.entities.Questions;
import com.saubantiago.personalmedicaldiary.database.view.QuestionsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;
    QuestionsViewModel questionsViewModel;
    List<Questions> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addQuestions();
    }

    private void addQuestions() {
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        questionsViewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> _questions) {
                questions = _questions;
                if(questions.isEmpty()) {
                    Questions q1 = new Questions("Do you have a cough?");
                    Questions q2 = new Questions("Do you have a sore throat?");
                    Questions q3 = new Questions("Do you have a fever?");
                    questionsViewModel.insert(q1);
                    questionsViewModel.insert(q2);
                    questionsViewModel.insert(q3);
                    System.out.println("All questions inserted");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.isLoggedIn();
    }

    private void isLoggedIn() {
        Class activityClass = FirebaseAuth.getInstance().getCurrentUser() == null ? LoginActivity.class :  Dashboard.class;
        Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}