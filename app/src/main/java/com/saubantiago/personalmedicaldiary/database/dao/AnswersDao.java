package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.Answers;

import java.util.List;

@Dao
public interface AnswersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Answers answers);

    @Update
    public void update(Answers... answers);

    @Delete
    void delete(Answers answers);

    @Query("DELETE FROM answers")
    void deleteAll();

    @Query("SELECT * from answers ORDER BY id ASC")
    LiveData<List<Answers>> getAll();

    @Query("SELECT * FROM answers WHERE assessment_id LIKE :assessmentID")
    public LiveData<List<Answers>> getAllAnswersByID(long assessmentID);

    @Query("SELECT * FROM answers WHERE assessment_id LIKE :assessmentID")
    public List<Answers> getAllStaticAnswers(long assessmentID);

}