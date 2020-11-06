package com.saubantiago.personalmedicaldiary;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

/**
 * Manages User Session, Data and Generic app preferences
 */
public class SessionManager {
    static final String PREF_USER_EMAIL = "user_email";
    static final String PREF_USER_ID = "user_id";
    static final String PREF_USER_STATUS = "user_status";

    // static final long timeToExpire = 1000 * 60 * 60 * 24 * 30; // remain logged in 30 days
    static final long timeToExpire = 1000 * 60 * 5;               // 5min


    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setLoggedInUserEmail(Context ctx, String email) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.commit();
    }

    public static String getLoggedInUserEmail(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static void setLoggedInUserId(Context ctx, int id) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_ID, id);
        editor.commit();
    }

    public static int getLoggedInUserId(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_ID, -1);
    }

    public static void setLoggedInUserStatus(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_USER_STATUS, status);
        editor.commit();
    }

    public static boolean getLoggedInUserStatus(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_USER_STATUS, false);
    }

    public static void clearLoggedInUserSession(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_USER_EMAIL);
        editor.remove(PREF_USER_ID);
        editor.remove(PREF_USER_STATUS);
        editor.commit();
    }

    public static boolean loggedInExpired() {
        // TODO implment logic to auto logout after certain duration
        return false;
    }
}
