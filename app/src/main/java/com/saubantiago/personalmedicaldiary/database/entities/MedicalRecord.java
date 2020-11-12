package com.saubantiago.personalmedicaldiary.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medical_record")
public class MedicalRecord implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "user_uid")
    private String userUID;

    @ColumnInfo(name = "file_type")
    private String fileType;

    @ColumnInfo(name = "file_location")
    private String fileLocation;

    @ColumnInfo(name = "medical_record_type")
    private String medicalRecordType;

    @ColumnInfo(name = "created_at")
    private long createdAt;

    @ColumnInfo(name = "updated_at")
    private long updatedAt;

    @Ignore
    public MedicalRecord(String fileType,String medicalRecordType) {
        this.fileType = fileType;
        this.medicalRecordType = medicalRecordType;
    }

    public MedicalRecord(String fileType, String fileLocation, String medicalRecordType) {
        this.fileType = fileType;
        this.fileLocation = fileLocation;
        this.medicalRecordType = medicalRecordType;
    }

    @Ignore
    public MedicalRecord(long id, String fileType, String fileLocation, String medicalRecordType) {
        this.id = id;
        this.fileType = fileType;
        this.fileLocation = fileLocation;
        this.medicalRecordType = medicalRecordType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getMedicalRecordType() {
        return medicalRecordType;
    }

    public void setMedicalRecordType(String medicalRecordType) {
        this.medicalRecordType = medicalRecordType;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", userUID='" + userUID + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                ", medicalRecordType='" + medicalRecordType + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
