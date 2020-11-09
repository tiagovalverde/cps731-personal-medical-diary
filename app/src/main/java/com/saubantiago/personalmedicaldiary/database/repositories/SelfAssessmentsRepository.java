package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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

    /***************************************
     * CRUD ACTIONS
     ***************************************/
    public void insert (SelfAssessments selfAssessments) {
        new SelfAssessmentsRepository.insertAsyncTask(selfAssessmentsDao).execute(selfAssessments);
    }

    public void update(SelfAssessments selfAssessments)  {
        new SelfAssessmentsRepository.updateAsyncTask(selfAssessmentsDao).execute(selfAssessments);
    }

    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<SelfAssessments, Void, Void> {
        private SelfAssessmentsDao asyncTaskSelfAssessmentsDao;

        insertAsyncTask(SelfAssessmentsDao dao) {
            asyncTaskSelfAssessmentsDao = dao;
        }

        @Override
        protected Void doInBackground(SelfAssessments... selfAssessments) {
            asyncTaskSelfAssessmentsDao.insert(selfAssessments[0]);
            return null;
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
}