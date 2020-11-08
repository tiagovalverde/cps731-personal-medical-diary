package com.saubantiago.personalmedicaldiary.database.entities.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.User;

import java.util.List;

public class UserAndCaregivers {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "user_id",
            entity = Caregiver.class
    )
    public List<Caregiver> caregivers;

    @Override
    public String toString() {
        return "UserAndCaregivers{" +
                "user=" + user +
                ", caregivers=" + caregivers +
                '}';
    }
}
