package com.saubantiago.personalmedicaldiary.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "patient_profile")
public class PatientProfile implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "weight")
    private String weight;

    @ColumnInfo(name = "height")
    private String height;

    @ColumnInfo(name = "medication")
    private String medication;

    @ColumnInfo(name = "allergies")
    private String allergies;

    @ColumnInfo(name = "family_history")
    private String familyHistory;

    @ColumnInfo(name = "medical_history")
    private String medicalHistory;

    // Enum (dropdown selected)
    @ColumnInfo(name = "blood_type")
    private String bloodType;

    // TODO add user relationship
    //private int userId;


    public PatientProfile(
            String weight,
            String height,
            String medication,
            String allergies,
            String familyHistory,
            String medicalHistory,
            String bloodType) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.medication = medication;
        this.allergies = allergies;
        this.familyHistory = familyHistory;
        this.medicalHistory = medicalHistory;
        this.bloodType = bloodType;
    }

    @Ignore
    public PatientProfile(
            int id,
            String weight,
            String height,
            String medication,
            String allergies,
            String familyHistory,
            String medicalHistory,
            String bloodType) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.medication = medication;
        this.allergies = allergies;
        this.familyHistory = familyHistory;
        this.medicalHistory = medicalHistory;
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "PatientProfile{" +
                "id=" + id +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", medication='" + medication + '\'' +
                ", allergies='" + allergies + '\'' +
                ", familyHistory='" + familyHistory + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
