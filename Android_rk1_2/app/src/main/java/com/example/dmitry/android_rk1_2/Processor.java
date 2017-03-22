package com.example.dmitry.android_rk1_2;

import java.io.IOException;

/**
 * Created by dmitry on 21.03.17.
 */

public class Processor {
    private Processor() {}

    private static Processor instance;

    public static Processor getInstance() {
        if (instance == null) {
            instance = new Processor();
        }
        return instance;
    }

    Cource getNewCource() {
        Cource result;
        String settings = Storage.getInstance(null).getSettings();
        try {
            result = Rest.getInstance().sendRequest(settings);
            Storage.getInstance(null).saveCource(result);
        }
        catch (IOException e) {
            result = Storage.getInstance(null).getCource();
            if (result == null) {
                result = new Cource();
            }
            else if (!settings.equals(result.currency)) {
                result.status = Cource.ERROR;
            }
        }
        return result;
    }
}
