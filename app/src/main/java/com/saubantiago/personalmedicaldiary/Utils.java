package com.saubantiago.personalmedicaldiary;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Utils {
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean emailValid(EditText view, String email) {
        if (email.isEmpty()) {
            if (view != null) {
                view.setError("Email is required");
                view.requestFocus();
            }

            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (view != null) {
                view.setError("Please provide a valid email");
                view.requestFocus();
            }
            return false;
        }

        return true;
    }

    public static boolean passwordValid(EditText view, String password) {
        if (password.isEmpty()) {
            if (view != null) {
                view.setError("Password is required");
                view.requestFocus();
            }

            return false;
        }

        if (password.length() < 8) {
            if (view != null) {
                view.setError("Password needs at least 8 characters");
                view.requestFocus();
            }
            return false;
        }

        return true;
    }

    public static String formattedDateTime(long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time));
    }
}
