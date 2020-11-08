package com.saubantiago.personalmedicaldiary.database.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndCaregivers;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndPatientProfile;
import com.saubantiago.personalmedicaldiary.database.repositories.UserRepository;

import java.util.List;

public class UserViewModal extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<UserAndPatientProfile>> allUsersAndProfile;

    public UserViewModal(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
        allUsersAndProfile = repository.getAllUsersAndProfile();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<UserAndPatientProfile>> getAllUsersAndProfile() {
        return allUsersAndProfile;
    }

    public LiveData<List<UserAndCaregivers>> getAllUsersAndCaregivers() {
        return repository.getAllUsersAndCaregivers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void insertProfile(User user, PatientProfile patientProfile) {
        repository.insertProfile(user, patientProfile);
    }

    public void updateProfile(User user, PatientProfile patientProfile) {
        repository.updateProfile(user, patientProfile);
    }

    public void insertCaregiver(User user, Caregiver caregiver) {
        repository.insertCaregiver(user, caregiver);
    }

    public void updateCaregiver(User user, Caregiver caregiver) {
        repository.updateCaregiver(user, caregiver);

    }
}
