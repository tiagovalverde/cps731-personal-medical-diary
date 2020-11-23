package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.repositories.AnswersRepository;

import java.util.List;

public class AnswersViewModel extends AndroidViewModel {

    private AnswersRepository repository;

    private LiveData<List<Answers>> allAnswers;

    public AnswersViewModel(@NonNull Application application) {
        super(application);
        repository = new AnswersRepository(application);
        allAnswers = repository.getAllAnswers();
    }

    public LiveData<List<Answers>> getAnswersByAssessmentID(Long id) {
        return repository.getAnswersByAssessmentID(id);
    }


    public void insert(Answers answers) {
        repository.insert(answers);
    }

    public void update(Answers answers) {
        repository.update(answers);
    }

    public void delete(Answers answers) {
        repository.delete(answers);
    }
}