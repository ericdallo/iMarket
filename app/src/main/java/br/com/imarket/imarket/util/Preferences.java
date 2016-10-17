package br.com.imarket.imarket.util;

import android.app.Activity;

import java.util.Set;

import br.com.imarket.imarket.MainActivity;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {

    public static final String COOKIES = "PREF_COOKIES";
    public static final String HIDE_WELCOME = "PREF_WELCOME";

    private static Activity activity;

    public static void putStringSet(String key, Set<String> hashValues) {
        activity.getPreferences(MODE_PRIVATE).edit().putStringSet(key, hashValues).apply();
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValues) {
        return activity.getPreferences(MODE_PRIVATE).getStringSet(key, defaultValues);
    }

    public static boolean getBoolean(String key) {
        return getBooleanValue(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getBooleanValue(key, defaultValue);
    }

    private static boolean getBooleanValue(String key, boolean defaultValue) {
        return activity.getPreferences(MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        activity.getPreferences(MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    public static void setActivity(Activity activity) {
        Preferences.activity = activity;
    }
}
