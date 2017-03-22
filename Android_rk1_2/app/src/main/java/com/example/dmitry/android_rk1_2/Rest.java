package com.example.dmitry.android_rk1_2;

import android.net.Uri;

import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dmitry on 21.03.17.
 */

public class Rest {
    private final static String API_KEY = "6kgxa66uGemshT44k8Z0KZphp6kKp1c9qAnjsnh7L9ZHq2b4Nk";
    private final static String METHOD_URL = "https://community-bitcointy.p.mashape.com";
    private static Rest instance;

    private Rest(){};

    public static Rest getInstance() {
        if (instance == null) {
            instance = new Rest();
        }
        return instance;
    }

    public Cource sendRequest(String currency) throws IOException {
        InputStream is = null;
        try {
            final String uri = Uri.parse(METHOD_URL  + "/average/" + currency)
                    .buildUpon()
                    .build()
                    .toString();
            HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("X-Mashape-Key", API_KEY);
            conn.setRequestProperty("Accept", "text/plain");
            conn.connect();
            int responseCode = conn.getResponseCode();
            is = conn.getInputStream();
            if (responseCode == 200) {
                return inputStreamToString(is);
            }
        }
        finally {
            if(is != null) {
                is.close();
            }
        }
        return null;
    }

    private static Cource inputStreamToString(final InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        String res = stringBuilder.toString();
        Cource cource = new Cource();
        try {
            JSONObject jsonObject = new JSONObject(res);
            cource.currency = jsonObject.getString("currency");
            cource.value = Integer.toString(jsonObject.getInt("value"));
            cource.status = Cource.OK;
        }
        catch (Exception e) {
            cource.status = Cource.ERROR;
        }
        return cource;
    }
}
