package com.saubantiago.personalmedicaldiary.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "self_assessments")
public class SelfAssessments implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "AssessmentType")
    private String assessmentType;

    @ColumnInfo(name = "user_uid")
    private String userUID;


    public SelfAssessments(String assessmentType) {
        this.assessmentType = assessmentType;

    }

    @Ignore
    public SelfAssessments(int id, String assessmentType) {
        this.id = id;
        this.assessmentType = assessmentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssessmentType() {
        return assessmentType ;
    }

    public void setAssessmentType(String assessmentType){
        this.assessmentType = assessmentType;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserUID() {
        return userUID;
    }

    @Override
    public String toString() {
        return "SelfAssessments{" +
                "id=" + id +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}