package com.saubantiago.personalmedicaldiary.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.saubantiago.personalmedicaldiary.database.dao.AnswersDao;
import com.saubantiago.personalmedicaldiary.database.dao.CaregiverDao;
import com.saubantiago.personalmedicaldiary.database.dao.MedicalRecordDao;
import com.saubantiago.personalmedicaldiary.database.dao.PatientProfileDao;
import com.saubantiago.personalmedicaldiary.database.dao.QuestionsDao;
import com.saubantiago.personalmedicaldiary.database.dao.SelfAssessmentsDao;

import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.entities.Questions;
import com.saubantiago.personalmedicaldiary.database.entities.Answers;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;

@Database(
        entities = {
                PatientProfile.class,
                Caregiver.class,
                Questions.class,
                Answers.class,
                SelfAssessments.class,
                MedicalRecord.class
        },
        version = 1
)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract PatientProfileDao patientProfileDao();
    public abstract CaregiverDao caregiverDao();
    public abstract QuestionsDao questionsDao();
    public abstract AnswersDao answersDao();
    public abstract SelfAssessmentsDao selfAssessmentsDao();
    public abstract MedicalRecordDao medicalRecord();

    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "personal_medical_diary_dev")
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
