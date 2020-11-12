package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.dao.PatientProfileDao;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.util.List;

public class PatientProfileRepository {

    private PatientProfileDao patientProfileDao;

    public PatientProfileRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        patientProfileDao = db.patientProfileDao();
    }

    public LiveData<List<PatientProfile>> getAllPatientProfiles() {
        return patientProfileDao.getAll();
    }

    public LiveData<List<PatientProfile>> getAllPatientProfilesByUserUID(String userUID) {
        return patientProfileDao.getAllByUserUID(userUID);
    }

    /***************************************
     * CRUD ACTIONS
     ***************************************/
    public void insert (PatientProfile patientProfile) {
        new insertAsyncTask(patientProfileDao).execute(patientProfile);
    }

    public void update(PatientProfile patientProfile)  {
        new updateAsyncTask(patientProfileDao).execute(patientProfile);
    }


    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<PatientProfile, Void, Void> {

        private PatientProfileDao asyncTaskPatientProfileDao;

        insertAsyncTask(PatientProfileDao dao) {
            asyncTaskPatientProfileDao = dao;
        }

        @Override
        protected Void doInBackground(final PatientProfile... params) {
            asyncTaskPatientProfileDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<PatientProfile, Void, Void> {
        private PatientProfileDao asyncTaskPatientProfileDao;

        updateAsyncTask(PatientProfileDao dao) {
            asyncTaskPatientProfileDao = dao;
        }

        @Override
        protected Void doInBackground(final PatientProfile... params) {
            asyncTaskPatientProfileDao.update(params[0]);
            return null;
        }
    }
}
