package com.example.bookbub;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_LOGGED_IN = "isLoggedIn";
    private static final String KEY_PID = "pid";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_physical = "physical";
    private static final String KEY_profile = "profile";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private final Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String username,String pid,String gender,String physical,String profile) {
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PID, pid);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_physical, physical);
        editor.putString(KEY_profile, profile);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_LOGGED_IN, false);
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, null);
    }

    public  String getPid() {
        return pref.getString(KEY_PID, null);    }

    public  String getGender() {
        return pref.getString(KEY_GENDER, null);    }

    public void logoutUser() {
        editor.clear();
        editor.apply();
    }
    public  String getPhysical() {
        return pref.getString(KEY_physical, null);    }

    public  String getProfile() {
        return pref.getString(KEY_profile, null);    }


}
