package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
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
    void update(MedicalRecord... medicalRecords);

    @Delete
    void delete(MedicalRecord medicalRecord);

    @Query("DELETE FROM medical_record")
    void deleteAll();

    @Query("SELECT * from medical_record ORDER BY id ASC")
    LiveData<List<MedicalRecord>> getAll();

    @Query("SELECT * FROM medical_record WHERE user_uid LIKE :userUID ")
    LiveData<List<MedicalRecord>> getAllByUserUID(String userUID);
}
