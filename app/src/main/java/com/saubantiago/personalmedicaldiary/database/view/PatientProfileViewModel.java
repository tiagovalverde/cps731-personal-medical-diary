package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.repositories.PatientProfileRepository;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

import java.util.List;

public class PatientProfileViewModel extends AndroidViewModel {

    private PatientProfileRepository repository;

    private LiveData<List<PatientProfile>> allPatientProfiles;

    public PatientProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientProfileRepository(application);
        allPatientProfiles = repository.getAllPatientProfiles();
    }

    public LiveData<List<PatientProfile>> getAllPatientProfiles() {
        return allPatientProfiles;
    }

    public void insert(PatientProfile patientProfile) {
        repository.insert(patientProfile);
    }

    public void update(PatientProfile patientProfile) {
        repository.update(patientProfile);
    }
}
