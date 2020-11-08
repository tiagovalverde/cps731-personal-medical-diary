package com.saubantiago.personalmedicaldiary.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndCaregivers;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndPatientProfile;

import java.util.List;

@Dao
public abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(User user);

    // Update multiple entries with one call.
    @Update
    public abstract void update(User... users);

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM users")
    public abstract void deleteAll();

    // Simple query without parameters that returns values.
    @Query("SELECT * from users ORDER BY id ASC")
    public abstract LiveData<List<User>> getAllUsers();

    // Query with parameter that returns a specific word or words.
    @Query("SELECT * FROM users WHERE id LIKE :id ")
    public abstract List<User> findUser(int id);

    /**************************
     * Patient Profile Queries
     **************************/
    @Transaction
    @Query("SELECT * FROM users")
    public abstract LiveData<List<UserAndPatientProfile>> getUsersAndProfile();

    @Insert
    public abstract void insertProfile(PatientProfile patientProfile);

    @Update
    public abstract void updateProfile(PatientProfile patientProfile);

    /**************************
     * Caregivers Queries
     **************************/
    @Transaction
    @Query("SELECT * FROM users")
    public abstract LiveData<List<UserAndCaregivers>> getUsersAndCaregivers();

    @Insert
    public abstract void insertCaregiver(Caregiver caregiver);

    @Update
    public abstract void updateCaregiver(Caregiver caregiver);
}
