package com.saubantiago.personalmedicaldiary;

import android.content.Context;
import android.widget.Toast;

import com.saubantiago.personalmedicaldiary.database.entities.User;

import java.util.List;

public class Utils {

    public static boolean emailValid(Context ctx, String email) {
        String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.isEmpty()) {
            Toast.makeText(ctx, "Enter an email", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!email.trim().matches(EMAIL_PATTERN)) {
            Toast.makeText(ctx, "Email invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean passwordValid(Context ctx, String password) {
        int MIN_NUM_CHARS = 8;
        if(password.isEmpty()) {
            Toast.makeText(ctx, "Enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() < MIN_NUM_CHARS) {
            Toast.makeText(ctx, "Password needs at least 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean emailExists(List<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(Context ctx, List<User> users, String email, String password) {
        for(User user : users) {
            // finds email
            if (user.getEmail().equals(email)) {
                // check password
                if (PasswordManager.checkPassword(password, user.getPassword())) {
                    return user;
                } else {
                    Utils.showToast(ctx,"Wrong password!");
                    return null;
                }
            }
        }
        Utils.showToast(ctx,"Account with email does not exist!");
        return null;
    }

    public static void showToast(Context ctx, String text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }
}
