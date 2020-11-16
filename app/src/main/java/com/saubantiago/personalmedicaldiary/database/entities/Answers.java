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

    @ColumnInfo(name = "assessment_id")
    private String assessmentID;

    @ColumnInfo(name = "question_id")
    private String questionID;

    @Ignore
    public Answers(String answer) {
        this.answer = answer;

    }

    @Ignore
    public Answers(long id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Answers(long id, String answer, String assessmentID, String questionID) {
        this.id = id;
        this.answer = answer;
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