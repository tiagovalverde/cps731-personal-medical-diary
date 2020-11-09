package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.dao.UserDao;
import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndCaregivers;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndMedicalRecords;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndPatientProfile;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.sql.Date;
import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<UserAndPatientProfile>> allUsersAndProfile;

    public UserRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
        allUsersAndProfile = userDao.getUsersAndProfile();
    }

    public LiveData<List<User>> getAllUsers() { return allUsers; }

    public LiveData<List<UserAndPatientProfile>> getAllUsersAndProfile() {
        return allUsersAndProfile;
    }

    public LiveData<List<UserAndCaregivers>> getAllUsersAndCaregivers() {
        return userDao.getUsersAndCaregivers();
    }

    public LiveData<List<UserAndMedicalRecords>> getAllUsersAndMedicalRecords() {
        return userDao.getUsersAndMedicalRecords();
    }

    /***************************************
     * CRUD ACTIONS USER
     ***************************************/
    public void insert(User user) {
        new UserRepository.insertAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UserRepository.updateAsyncTask(userDao).execute(user);
    }

    /***************************************
     * CRUD ACTIONS PROFILE
     ***************************************/
    public void insertProfile(User user, PatientProfile patientProfile) {
        patientProfile.setUserId(user.getId());
        new UserRepository.insertProfileAsyncTask(userDao).execute(patientProfile);
    }

    public void updateProfile(User user, PatientProfile patientProfile) {
        patientProfile.setUserId(user.getId());
        new UserRepository.updateProfileAsyncTask(userDao).execute(patientProfile);
    }

    /***************************************
     * CRUD ACTIONS CAREGIVER
     ***************************************/
    public void insertCaregiver(User user, Caregiver caregiver) {
        caregiver.setUserId(user.getId());
        new UserRepository.insertCaregiverAsyncTask(userDao).execute(caregiver);
    }

    public void updateCaregiver(User user, Caregiver caregiver) {
        caregiver.setUserId(user.getId());
        new UserRepository.updateCaregiverAsyncTask(userDao).execute(caregiver);
    }

    /***************************************
     * ASYNC TASKS USER
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncTaskUserDao;

        insertAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskUserDao.insert(users[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncTaskUserDao;

        updateAsyncTask(UserDao dao) {
            asyncTaskUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskUserDao.update(users[0]);
            return null;
        }
    }

    /***************************************
     * ASYNC TASKS USER PROFILE
     ***************************************/
    private static class insertProfileAsyncTask extends AsyncTask<PatientProfile, Void, Void> {
        private UserDao asyncTaskUserDao;

        insertProfileAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(PatientProfile... patientProfiles) {
            asyncTaskUserDao.insertProfile(patientProfiles[0]);
            return null;
        }
    }

    private static class updateProfileAsyncTask extends AsyncTask<PatientProfile, Void, Void> {
        private UserDao asyncTaskUserDao;

        updateProfileAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(PatientProfile... patientProfiles) {
            asyncTaskUserDao.updateProfile(patientProfiles[0]);
            return null;
        }
    }

    /***************************************
     * ASYNC TASKS USER CAREGIVERS
     ***************************************/
    private static class insertCaregiverAsyncTask extends AsyncTask<Caregiver, Void, Void> {
        private UserDao asyncTaskUserDao;

        insertCaregiverAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(Caregiver... caregivers) {
            asyncTaskUserDao.insertCaregiver(caregivers[0]);
            return null;
        }
    }

    private static class updateCaregiverAsyncTask extends AsyncTask<Caregiver, Void, Void> {
        private UserDao asyncTaskUserDao;

        updateCaregiverAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(Caregiver... caregivers) {
            asyncTaskUserDao.updateCaregiver(caregivers[0]);
            return null;
        }
    }

    /***************************************
     * MEDICAL RECORD
     ***************************************/
    public void insertMedicalRecord(User user, MedicalRecord medicalRecord) {
        medicalRecord.setUserId(user.getId());

        long now = new Date(System.currentTimeMillis()).getTime();
        medicalRecord.setCreatedAt(now);
        medicalRecord.setUpdatedAt(now);

        new UserRepository.insertMedicalRecordAsyncTask(userDao).execute(medicalRecord);
    }

    public void updateMedicalRecord(User user, MedicalRecord medicalRecord) {
        medicalRecord.setUserId(user.getId());
        medicalRecord.setUpdatedAt(new Date(System.currentTimeMillis()).getTime());
        new UserRepository.updateMedicalRecordAsyncTask(userDao).execute(medicalRecord);
    }

    private static class insertMedicalRecordAsyncTask extends AsyncTask<MedicalRecord, Void, Void> {
        private UserDao asyncTaskUserDao;

        insertMedicalRecordAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(MedicalRecord... medicalRecords) {
            asyncTaskUserDao.insertMedicalRecord(medicalRecords[0]);
            return null;
        }
    }

    private static class updateMedicalRecordAsyncTask extends AsyncTask<MedicalRecord, Void, Void> {
        private UserDao asyncTaskUserDao;

        updateMedicalRecordAsyncTask(UserDao dao) { asyncTaskUserDao = dao; }

        @Override
        protected Void doInBackground(MedicalRecord... medicalRecords) {
            asyncTaskUserDao.updateMedicalRecord(medicalRecords[0]);
            return null;
        }
    }
}
