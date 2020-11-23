package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.repositories.SelfAssessmentsRepository;

import java.util.List;

public class SelfAssessmentsViewModel extends AndroidViewModel {

    private SelfAssessmentsRepository repository;
    private MutableLiveData<Long> currentId;

    //private LiveData<List<SelfAssessments>> allSelfAssessments;

    public SelfAssessmentsViewModel(@NonNull Application application) {
        super(application);
        repository = new SelfAssessmentsRepository(application);
        currentId = new MutableLiveData<Long>();

        //allSelfAssessments = repository.getAllSelfAssessments();
    }

    public void insert(SelfAssessments selfAssessments) {
        repository.insert(currentId, selfAssessments);
    }

    public void update(SelfAssessments selfAssessments) {
        repository.update(selfAssessments);
    }

    public void delete(SelfAssessments selfAssessments) {
        repository.delete(selfAssessments);
    }
    public LiveData<List<SelfAssessments>> getAllAssessmentsByUserUID(String userUID) {
        return repository.getAllByUserUID(userUID);
    }

    public MutableLiveData<Long> getCurrentId(){
        return currentId;
    }

    /**
     public LiveData<List<SelfAssessments>> getAllSelfAssessments() {
     return allSelfAssessments;
     }
     **/
}
