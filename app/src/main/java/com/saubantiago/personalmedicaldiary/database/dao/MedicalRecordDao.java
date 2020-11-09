package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;

import java.util.List;

@Dao
public interface MedicalRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MedicalRecord medicalRecord);

    @Update
    public void update(MedicalRecord... medicalRecords);

    @Query("DELETE FROM medical_record")
    void deleteAll();

    @Query("SELECT * from medical_record ORDER BY id ASC")
    LiveData<List<MedicalRecord>> getAll();

    @Query("SELECT * FROM medical_record WHERE id LIKE :id ")
    public List<MedicalRecord> findMedicalRecord(int id);
}