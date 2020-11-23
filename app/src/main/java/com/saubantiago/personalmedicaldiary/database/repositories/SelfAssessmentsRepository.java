package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saubantiago.personalmedicaldiary.database.dao.SelfAssessmentsDao;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.util.List;

public class SelfAssessmentsRepository {

    private SelfAssessmentsDao selfAssessmentsDao;
    private LiveData<List<SelfAssessments>> allSelfAssessments;

    public SelfAssessmentsRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        selfAssessmentsDao = db.selfAssessmentsDao();
        allSelfAssessments = selfAssessmentsDao.getAll();
    }

    public LiveData<List<SelfAssessments>> getAllSelfAssessments() {
        return allSelfAssessments;
    }

    public LiveData<List<SelfAssessments>> getAllByUserUID(String userUID) {
        return selfAssessmentsDao.getAllByUserUID(userUID);
    }

    /***************************************
     * CRUD ACTIONS
     **************************************
     * @return*/
    public void insert(MutableLiveData<Long> id, SelfAssessments selfAssessments) {
        new SelfAssessmentsRepository.insertAsyncTask(id, selfAssessmentsDao).execute(selfAssessments);
    }


    public void update(SelfAssessments selfAssessments)  {
        new SelfAssessmentsRepository.updateAsyncTask(selfAssessmentsDao).execute(selfAssessments);
    }
    public void delete(SelfAssessments selfAssessments) {
        new SelfAssessmentsRepository.deleteAsyncTask(selfAssessmentsDao).execute(selfAssessments);
    }

    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<SelfAssessments, Void, Long> {
        private SelfAssessmentsDao asyncTaskSelfAssessmentsDao;
        private MutableLiveData<Long> id;

        private insertAsyncTask(MutableLiveData<Long> id, SelfAssessmentsDao dao) {
            this.asyncTaskSelfAssessmentsDao = dao;
            this.id = id;
        }

        @Override
        protected Long doInBackground(SelfAssessments... selfAssessments) {
            return asyncTaskSelfAssessmentsDao.insert(selfAssessments[0]);
        }

        @Override
        protected void onPostExecute(Long insertId) {
            id.postValue(insertId);
            super.onPostExecute(insertId);
        }

        @Override
        protected void onCancelled() {
            id.postValue(new Long(0));
            super.onCancelled();
        }
    }

    private static class updateAsyncTask extends AsyncTask<SelfAssessments, Void, Void> {
        private SelfAssessmentsDao asyncTaskSelfAssessmentsDao;

        updateAsyncTask(SelfAssessmentsDao dao) {
            asyncTaskSelfAssessmentsDao = dao;
        }

        @Override
        protected Void doInBackground(SelfAssessments... selfAssessments) {
            asyncTaskSelfAssessmentsDao.update(selfAssessments[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<SelfAssessments, Void, Void> {
        private SelfAssessmentsDao asyncTaskDao;

        deleteAsyncTask(SelfAssessmentsDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SelfAssessments... selfAssessments) {
            asyncTaskDao.delete(selfAssessments[0]);
            return null;
        }
    }
}