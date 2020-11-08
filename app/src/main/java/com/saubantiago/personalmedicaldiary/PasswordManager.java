package com.saubantiago.personalmedicaldiary;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordManager {
    private static int COST = 12;

    public static String hashPassword(String password) {
        String hash = BCrypt.withDefaults().hashToString(COST, password.toCharArray());
        return hash;
    }

    public static boolean checkPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
}
