package br.com.kmg.bakingapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManagerUtil {


    public static String getPreference(Context context, String key, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }


    public static void setPreference(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

}
