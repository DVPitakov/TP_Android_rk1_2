package com.example.dmitry.android_rk1_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by dmitry on 21.03.17.
 */

public class ServiceHelper implements AppResultReceiver.Receiver {
    private ServiceHelper() {}


    public final static String RECEIVER = "RECEIVER";

    interface ServiceHelperListener {
        void onServiceHelperResult(Bundle data);

    }

    private static AppResultReceiver appResultReceiver;
    private ServiceHelperListener listener;
    private static ServiceHelper instance;

    void sendRequest(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), MyIntentService.class);
        appResultReceiver.setReceiver(this);
        intent.putExtra(RECEIVER, appResultReceiver);
        context.startService(intent);

    }

    synchronized public static ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
            appResultReceiver = new AppResultReceiver(new Handler());
        }
        return instance;

    }

    public void setListener(ServiceHelperListener listener) {
        this.listener = listener;
    }

    public void removeListener() {
        listener = null;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        if (listener != null) {
            listener.onServiceHelperResult(data);
        }
    }

}
