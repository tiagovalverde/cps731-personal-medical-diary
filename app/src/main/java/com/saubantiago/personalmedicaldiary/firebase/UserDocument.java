package com.saubantiago.personalmedicaldiary.firebase;

public class UserDocument {
    public String email, password;

    public UserDocument(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
