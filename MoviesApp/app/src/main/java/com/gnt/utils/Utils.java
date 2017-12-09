package com.gnt.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageButton;

import com.gnt.moviesapp.HomeActivity;

/**
 * Created by sissy on 12/5/17.
 */

public class Utils {
    public static String USER_LOGIN_PREFERENCES         = "login_preferences";
    public static boolean isTokenExpired(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        boolean isExpired = retrofitCalls.isTokenExpired(token);
        return isExpired;
    }

    public static void logout(Activity context)
    {
    /* Reset Shared Preferences */
        SharedPreferences sharedPrefs = context.getApplicationContext().getSharedPreferences(USER_LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();

        // reset the retrofit client - the current one's key has to be deleted
        // since the getClient initialization was done only if retrofit was null, we set it to null.
        RetrofitClient.resetClient();

        Intent homeIntent = new Intent(context, HomeActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(homeIntent);
        context.finish();
    }

    public static Session getSessionData(Activity context) {
        SharedPreferences sharedPrefs = context.getApplicationContext().getSharedPreferences(USER_LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        Session sessionData = new Session(sharedPrefs.getString("token", ""), sharedPrefs.getString("username", ""),
                sharedPrefs.getBoolean("userLoggedInState", false));
        return sessionData;
    }
    /** Initialization of SharedPreferences when user selects to register or to login **/
    public static Session updateSessionData(Activity context, Session sessionData) {
        SharedPreferences sharedPrefs = context.getApplicationContext().getSharedPreferences(USER_LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("token", sessionData.getToken());
        editor.putString("username", sessionData.getUsername());
        editor.putBoolean("userLoggedInState", sessionData.getUserLoggedInState());
        editor.commit();

        return sessionData;
    }
}
