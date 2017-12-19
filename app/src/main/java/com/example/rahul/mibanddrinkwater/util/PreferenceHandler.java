package com.example.rahul.mibanddrinkwater.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rahul on 5/12/17.
 */

public class PreferenceHandler {
    private static final String EMAIL = "userEmail";
    private static final String PASSWORD = "userPassword";
    private static final String AUTH_TOKEN = "authToken";
    private static final String SHARED_PREF_NAME = "Drink_Water";

    //Preference Methods
    private static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    //Set Preferences
    public static void setStringSharedPreference(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static void saveAuthToken(Context context, String authToken) {
        setStringSharedPreference(context, AUTH_TOKEN, authToken);
    }

    public static String getAuthToken(Context context) {
        SharedPreferences preferences = getPreference(context);
        return preferences.getString(AUTH_TOKEN, "");
    }

}
