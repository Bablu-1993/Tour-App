package com.fmsfrontend.ltctours.prefrence;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.fmsfrontend.ltctours.activityClass.LoginActivity;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "spfName";
    private static final String KEY_ID = "id";

    private static SharedPrefManager mInstance;
    private static Context context;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    public static void logoutSession() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, LoginActivity.class));

    }
    public Boolean isLoggedIn(){
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getBoolean("loggedin", false);
    }

    public static void setLoggedIn(boolean b){
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit().putBoolean("loggedin",b).apply();
    }
}