package com.example.dmitry.android_rk1_2;

/**
 * Created by dmitry on 22.03.17.
 */

public class Cource {
    public final static int ERROR = 0;
    public final static int OK = 1;
    public final static int FROM_CACHE = 2;

    public final static String CURRENCYE_RUB = "RUB";
    public final static String CURRENCYE_USD = "USD";

    public final static String COURCE_VALUE = "Value";
    public final static String COURCE_CURRENCY = "Currency";
    public final static String COURCE_STATUS = "Status";

    public Cource() {
        status = Cource.ERROR;
        value = null;
        currency = null;
    }

    public String value;
    public String currency;
    public int status;
}
