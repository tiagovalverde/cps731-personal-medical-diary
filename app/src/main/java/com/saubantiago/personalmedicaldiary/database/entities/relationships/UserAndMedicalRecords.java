package com.saubantiago.personalmedicaldiary.database.entities.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.User;

import java.util.List;

public class UserAndMedicalRecords {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "id",
            entityColumn = "user_id",
            entity = MedicalRecord.class
    )
    public List<MedicalRecord> medicalRecords;

    @Override
    public String toString() {
        return "UserAndMedicalRecords{" +
                "user=" + user +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
