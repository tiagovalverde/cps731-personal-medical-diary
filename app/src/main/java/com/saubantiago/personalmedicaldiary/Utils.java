package com.saubantiago.personalmedicaldiary;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.saubantiago.personalmedicaldiary.database.entities.User;

import java.util.List;

public class Utils {

    public static boolean emailValid(EditText view, String email) {
        if (email.isEmpty()) {
            view.setError("Email is required");
            view.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.setError("Please provide a valid email");
            view.requestFocus();
            return false;
        }

        return true;
    }

    public static boolean passwordValid(EditText view, String password) {
        if (password.isEmpty()) {
            view.setError("Password is required");
            view.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            view.setError("Password needs at least 8 characters");
            view.requestFocus();
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
