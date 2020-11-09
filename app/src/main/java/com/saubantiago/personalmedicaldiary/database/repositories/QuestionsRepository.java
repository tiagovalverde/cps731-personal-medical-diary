package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.dao.QuestionsDao;
import com.saubantiago.personalmedicaldiary.database.entities.Questions;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.util.List;

public class QuestionsRepository {

    private QuestionsDao questionsDao;
    private LiveData<List<Questions>> allQuestions;

    public QuestionsRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        questionsDao = db.questionsDao();
        allQuestions = questionsDao.getAll();
    }

    public LiveData<List<Questions>> getAllQuestions() {
        return allQuestions;
    }

    /***************************************
     * CRUD ACTIONS
     ***************************************/
    public void insert (Questions questions) {
        new QuestionsRepository.insertAsyncTask(questionsDao).execute(questions);
    }

    public void update(Questions questions)  {
        new QuestionsRepository.updateAsyncTask(questionsDao).execute(questions);
    }

    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<Questions, Void, Void> {
        private QuestionsDao asyncTaskQuestionsDao;

        insertAsyncTask(QuestionsDao dao) {
            asyncTaskQuestionsDao = dao;
        }

        @Override
        protected Void doInBackground(Questions... questions) {
            asyncTaskQuestionsDao.insert(questions[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Questions, Void, Void> {
        private QuestionsDao asyncTaskQuestionsDao;

        updateAsyncTask(QuestionsDao dao) {
            asyncTaskQuestionsDao = dao;
        }

        @Override
        protected Void doInBackground(Questions... questions) {
            asyncTaskQuestionsDao.update(questions[0]);
            return null;
        }
    }
}