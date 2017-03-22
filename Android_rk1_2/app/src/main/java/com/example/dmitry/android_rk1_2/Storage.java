package com.example.dmitry.android_rk1_2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dmitry on 21.03.17.
 */

public class Storage {
    private static final String KEY_TOPIC = "Topic";
    private static final String KEY_NEWS = "News";
    private static final String KEY_UPDATE_IN_BG = "UpdateInBg";
    private static Storage instance;
    private SharedPreferences preferences;

    private Storage(Context context) {
        this.preferences = context.getSharedPreferences("News", 0);
    }

    public static synchronized Storage getInstance(Context context) {
        if(instance == null) {
            instance  = new Storage(context.getApplicationContext());
        }

        return instance;
    }


    public void saveSettings(String currency) {
        if (currency == null) {
            this.preferences.edit().remove("Settings").apply();
        }
        else {
            this.preferences.edit().putString("Settings", currency).apply();
        }
    }

    public String getSettings() {
        return this.preferences.getString("Settings", "");
    }

    public void saveCource(Cource cource) {
        if (cource == null) {
            this.preferences.edit().remove("Currency").apply();
            this.preferences.edit().remove("Value").apply();
        }
        else {
            this.preferences.edit().putString("Currency", cource.currency).apply();
            this.preferences.edit().putString("Value", cource.value).apply();
        }
    }

    public Cource getCource() {
        Cource cource = new Cource();
        cource.status = Cource.FROM_CACHE;
        cource.currency = this.preferences.getString("Currency", "");
        cource.value = this.preferences.getString("Value", "");
        return cource;
    }


}
