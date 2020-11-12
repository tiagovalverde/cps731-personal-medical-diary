package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.repositories.CaregiverRepository;

import java.util.List;

public class CaregiverViewModel extends AndroidViewModel {

    private CaregiverRepository repository;

    private LiveData<List<Caregiver>> allCaregivers;

    public CaregiverViewModel(@NonNull Application application) {
       super(application);
       repository = new CaregiverRepository(application);
       allCaregivers = repository.getAllCaregivers();
    }

    public LiveData<List<Caregiver>> getAllCaregivers() {
        return allCaregivers;
    }

    public LiveData<List<Caregiver>> getAllCaregiversByUserUID(String userUID) {
        return repository.getAllCaregiversByUID(userUID);
    }

    public void insert(Caregiver caregiver) {
        repository.insert(caregiver);
    }

    public void update(Caregiver caregiver) {
        repository.update(caregiver);
    }
}
