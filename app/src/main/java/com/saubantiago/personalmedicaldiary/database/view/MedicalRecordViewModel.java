package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.repositories.MedicalRecordRepository;

import java.util.List;

public class MedicalRecordViewModel extends AndroidViewModel {

    private MedicalRecordRepository repository;

    public MedicalRecordViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicalRecordRepository(application);
    }

    public LiveData<List<MedicalRecord>> getAllCaregivers() {
        return repository.getAll();
    }

    public LiveData<List<MedicalRecord>> getAllCaregiversByUserUID(String userUID) {
        return repository.getAllByUserUID(userUID);
    }

    public void insert(MedicalRecord medicalRecord) {
        repository.insert(medicalRecord);
    }

    public void update(MedicalRecord medicalRecord) {
        repository.update(medicalRecord);
    }

    public void delete(MedicalRecord medicalRecord) {
        repository.delete(medicalRecord);
    }
}
