package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

import java.util.List;

@Dao
public interface CaregiverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Caregiver caregiver);

    @Update
    public void update(Caregiver... caregivers);

    @Query("DELETE FROM caregiver")
    void deleteAll();

    @Query("SELECT * from caregiver ORDER BY id ASC")
    LiveData<List<Caregiver>> getAll();

    @Query("SELECT * FROM caregiver WHERE id LIKE :id ")
    public List<Caregiver> findCaregiver(int id);
}
