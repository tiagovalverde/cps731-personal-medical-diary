package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;

import java.util.List;

@Dao
public interface SelfAssessmentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SelfAssessments selfAssessments);

    @Update
    void update(SelfAssessments... selfAssessments);

    @Delete
    void delete(SelfAssessments selfAssessments);

    @Query("DELETE FROM self_assessments")
    void deleteAll();

    @Query("SELECT * from self_assessments ORDER BY id ASC")
    LiveData<List<SelfAssessments>> getAll();

    @Query("SELECT * FROM self_assessments WHERE user_uid LIKE :userUID ")
    LiveData<List<SelfAssessments>> getAllByUserUID(String userUID);
}