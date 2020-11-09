package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.entities.Questions;
import com.saubantiago.personalmedicaldiary.database.repositories.QuestionsRepository;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel {

    private QuestionsRepository repository;

    private LiveData<List<Questions>> allQuestions;

    public QuestionsViewModel(@NonNull Application application) {
        super(application);
        repository = new QuestionsRepository(application);
        allQuestions = repository.getAllQuestions();
    }

    public LiveData<List<Questions>> getAllQuestions() {
        return allQuestions;
    }

    public void insert(Questions questions) {
        repository.insert(questions);
    }

    public void update(Questions questions) {
        repository.update(questions);
    }
}
