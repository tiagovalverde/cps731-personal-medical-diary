package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

import java.util.List;

@Dao
public interface PatientProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PatientProfile patientProfile);

    // Update multiple entries with one call.
    @Update
    public void updatePatientProfile(PatientProfile... words);

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM patient_profile")
    void deleteAll();

    // Simple query without parameters that returns values.
    @Query("SELECT * from patient_profile ORDER BY id ASC")
    LiveData<List<PatientProfile>> getAllPatientProfiles();

    // Query with parameter that returns a specific word or words.
    @Query("SELECT * FROM patient_profile WHERE id LIKE :id ")
    public List<PatientProfile> findPatientProfile(String id);
}
