package com.saubantiago.personalmedicaldiary.database.entities.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;
import com.saubantiago.personalmedicaldiary.database.entities.User;

import java.util.List;

public class UserAndPatientProfile {
    @Embedded public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "user_id",
            entity = PatientProfile.class
    )
    public List<PatientProfile> patientProfiles;

    @Override
    public String toString() {
        return "UserAndPatientProfile{" +
                "user=" + user +
                ", patientProfile=" + patientProfiles +
                '}';
    }
}
