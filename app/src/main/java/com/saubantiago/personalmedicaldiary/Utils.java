package com.saubantiago.personalmedicaldiary;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static boolean emailValid(Context ctx, String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.isEmpty()) {
            Toast.makeText(ctx, "Enter an email", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!email.trim().matches(emailPattern)) {
            Toast.makeText(ctx, "Email invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean passwordValid(String password) {
        // TODO
        return true;
    }
}
