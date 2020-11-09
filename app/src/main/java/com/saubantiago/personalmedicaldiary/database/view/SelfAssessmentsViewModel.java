package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.repositories.SelfAssessmentsRepository;

import java.util.List;

public class SelfAssessmentsViewModel extends AndroidViewModel {

    private SelfAssessmentsRepository repository;

    private LiveData<List<SelfAssessments>> allSelfAssessments;

    public SelfAssessmentsViewModel(@NonNull Application application) {
        super(application);
        repository = new SelfAssessmentsRepository(application);
        allSelfAssessments = repository.getAllSelfAssessments();
    }

    public LiveData<List<SelfAssessments>> getAllSelfAssessments() {
        return allSelfAssessments;
    }

    public void insert(SelfAssessments selfAssessments) {
        repository.insert(selfAssessments);
    }

    public void update(SelfAssessments selfAssessments) {
        repository.update(selfAssessments);
    }
}
