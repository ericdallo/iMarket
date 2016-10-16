package br.com.imarket.imarket.util;

import android.app.Activity;

import java.util.Set;

import br.com.imarket.imarket.MainActivity;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {
    public static final String PREF_COOKIES = "PREF_COOKIES";

    private static Activity activity;

    public static void putStringSet(String key, Set<String> hashValues) {
        activity.getPreferences(MODE_PRIVATE).edit().putStringSet(key, hashValues).apply();
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValues) {
        return activity.getPreferences(MODE_PRIVATE).getStringSet(key, defaultValues);
    }

    public static void setActivity(MainActivity activity) {
        Preferences.activity = activity;
    }
}
