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



    public SelfAssessments(String AssessmentType) {
        this.assessmentType = AssessmentType;

    }

    @Ignore
    public SelfAssessments(int id, String AssessmentType) {
        this.id = id;
        this.assessmentType = AssessmentType;
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

    @Override
    public String toString() {
        return "SelfAssessments{" +
                "id=" + id +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}