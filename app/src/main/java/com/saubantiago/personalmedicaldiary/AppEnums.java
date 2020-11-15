package com.saubantiago.personalmedicaldiary;

public class AppEnums {
    public enum Actions {
        CREATE,
        UPDATE,
        DELETE
    }

    public enum MedicalRecordType {
        IMAGE {
            public String toString() { return "IMAGE"; }
        },
        PDF {
            public String toString() { return "PDF"; }
        }
    }
}
