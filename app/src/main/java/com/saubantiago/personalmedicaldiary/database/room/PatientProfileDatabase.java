package com.saubantiago.personalmedicaldiary.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.saubantiago.personalmedicaldiary.database.dao.PatientProfileDao;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

/// TODO class name changes to host multiple entities
@Database(entities = {PatientProfile.class}, version = 1)
public abstract class PatientProfileDatabase extends RoomDatabase {

    public abstract PatientProfileDao patientProfileDao();

    private static PatientProfileDatabase INSTANCE;

    public static PatientProfileDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PatientProfileDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PatientProfileDatabase.class, "personal_medical_diary_dev")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
