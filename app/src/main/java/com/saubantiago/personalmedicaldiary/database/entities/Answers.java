package com.saubantiago.personalmedicaldiary.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "answers")
public class Answers implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo(name = "user_uid")
    private String userUID;

    @ColumnInfo(name = "assessment_id")
    private String assessmentID;

    @ColumnInfo(name = "question_id")
    private String questionID;

    public Answers(String answer) {
        this.answer = answer;

    }

    @Ignore
    public Answers(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Answers(int id, String answer, String userUID, String assessmentID, String questionID) {
        this.id = id;
        this.answer = answer;
        this.userUID = userUID;
        this.assessmentID = assessmentID;
        this.questionID = questionID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer ;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setAssessmentID(String assessmentID) {
        this.assessmentID= assessmentID;
    }

    public String getAssessmentID() {
        return assessmentID;
    }

    public void setQuestionID(String questionID) {this.questionID = questionID;}

    public String getQuestionID() {return questionID;}

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                '}';
    }
}