package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;

import java.util.List;

@Dao
public interface SelfAssessmentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SelfAssessments selfAssessments);

    @Update
    public void update(SelfAssessments... selfAssessments);

    @Query("DELETE FROM self_assessments")
    void deleteAll();

    @Query("SELECT * from self_assessments ORDER BY id ASC")
    LiveData<List<SelfAssessments>> getAll();

    @Query("SELECT * FROM self_assessments WHERE id LIKE :id ")
    public List<SelfAssessments> findSelfAssessments(int id);
}