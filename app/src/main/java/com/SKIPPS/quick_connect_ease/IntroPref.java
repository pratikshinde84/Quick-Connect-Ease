package com.SKIPPS.quick_connect_ease;

import android.content.Context;
import android.content.SharedPreferences;

public class IntroPref {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "xyz";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    public IntroPref(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setIsFirstTimeLaunch(boolean firstTimeLaunch) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, firstTimeLaunch);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}