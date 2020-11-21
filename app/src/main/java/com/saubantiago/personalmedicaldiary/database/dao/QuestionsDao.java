package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.Questions;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Questions questions);

    @Update
    public void update(Questions... questions);

    @Query("DELETE FROM questions")
    void deleteAll();

    @Query("SELECT * from questions ORDER BY id ASC")
    LiveData<List<Questions>> getAll();

    @Query("SELECT * FROM questions WHERE id LIKE :id ")
    public List<Questions> getQuestionsByID(int id);
}
