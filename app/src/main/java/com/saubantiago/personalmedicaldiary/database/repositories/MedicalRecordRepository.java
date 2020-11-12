package com.saubantiago.personalmedicaldiary.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saubantiago.personalmedicaldiary.database.dao.MedicalRecordDao;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.room.AppRoomDatabase;

import java.sql.Date;
import java.util.List;

public class MedicalRecordRepository {

    private MedicalRecordDao medicalRecordDao;

    public MedicalRecordRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        medicalRecordDao = db.medicalRecord();
    }

    public LiveData<List<MedicalRecord>> getAll() {
        return medicalRecordDao.getAll();
    }

    public LiveData<List<MedicalRecord>> getAllByUserUID(String userUID) {
        return medicalRecordDao.getAllByUserUID(userUID);
    }

    public void insert(MedicalRecord medicalRecord) {
        long now = new Date(System.currentTimeMillis()).getTime();
        medicalRecord.setCreatedAt(now);
        medicalRecord.setUpdatedAt(now);

        new MedicalRecordRepository.insertAsyncTask(medicalRecordDao).execute(medicalRecord);
    }

    public void update(MedicalRecord medicalRecord) {
        medicalRecord.setUpdatedAt(new Date(System.currentTimeMillis()).getTime());
        new MedicalRecordRepository.updateAsyncTask(medicalRecordDao).execute(medicalRecord);
    }

    /***************************************
     * ASYNC TASKS
     ***************************************/
    private static class insertAsyncTask extends AsyncTask<MedicalRecord, Void, Void> {
        private MedicalRecordDao asyncTaskDao;

        insertAsyncTask(MedicalRecordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MedicalRecord... medicalRecords) {
            asyncTaskDao.insert(medicalRecords[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<MedicalRecord, Void, Void> {
        private MedicalRecordDao asyncTaskDao;

        updateAsyncTask(MedicalRecordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MedicalRecord... medicalRecords) {
            asyncTaskDao.update(medicalRecords[0]);
            return null;
        }
    }
}
