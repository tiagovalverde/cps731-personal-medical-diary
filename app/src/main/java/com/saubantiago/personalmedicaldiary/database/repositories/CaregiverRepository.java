package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.dao.CaregiverDao;
import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.util.List;

public class CaregiverRepository {

    private CaregiverDao caregiverDao;
    private LiveData<List<Caregiver>> allCaregivers;

    public CaregiverRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        caregiverDao = db.caregiverDao();
        allCaregivers = caregiverDao.getAll();
    }

    public LiveData<List<Caregiver>> getAllCaregivers() {
        return allCaregivers;
    }

    /***************************************
     * CRUD ACTIONS
     ***************************************/
    public void insert (Caregiver caregiver) {
        new CaregiverRepository.insertAsyncTask(caregiverDao).execute(caregiver);
    }

    public void update(Caregiver caregiver)  {
        new CaregiverRepository.updateAsyncTask(caregiverDao).execute(caregiver);
    }

    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<Caregiver, Void, Void> {
        private CaregiverDao asyncTaskCaregiverDao;

        insertAsyncTask(CaregiverDao dao) {
            asyncTaskCaregiverDao = dao;
        }

        @Override
        protected Void doInBackground(Caregiver... caregivers) {
            asyncTaskCaregiverDao.insert(caregivers[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Caregiver, Void, Void> {
        private CaregiverDao asyncTaskCaregiverDao;

        updateAsyncTask(CaregiverDao dao) {
            asyncTaskCaregiverDao = dao;
        }

        @Override
        protected Void doInBackground(Caregiver... caregivers) {
            asyncTaskCaregiverDao.update(caregivers[0]);
            return null;
        }
    }
}
