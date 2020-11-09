package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.dao.AnswersDao;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.util.List;

public class AnswersRepository {

    private AnswersDao answersDao;
    private LiveData<List<Answers>> allAnswers;

    public AnswersRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        answersDao = db.answersDao();
        allAnswers = answersDao.getAll();
    }

    public LiveData<List<Answers>> getAllAnswers() {
        return allAnswers;
    }

    /***************************************
     * CRUD ACTIONS
     ***************************************/
    public void insert (Answers answers) {
        new AnswersRepository.insertAsyncTask(answersDao).execute(answers);
    }

    public void update(Answers answers)  {
        new AnswersRepository.updateAsyncTask(answersDao).execute(answers);
    }

    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<Answers, Void, Void> {
        private AnswersDao asyncTaskAnswersDao;

        insertAsyncTask(AnswersDao dao) {
            asyncTaskAnswersDao = dao;
        }

        @Override
        protected Void doInBackground(Answers... answers) {
            asyncTaskAnswersDao.insert(answers[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Answers, Void, Void> {
        private AnswersDao asyncTaskAnswersDao;

        updateAsyncTask(AnswersDao dao) {
            asyncTaskAnswersDao = dao;
        }

        @Override
        protected Void doInBackground(Answers... answers) {
            asyncTaskAnswersDao.update(answers[0]);
            return null;
        }
    }
}