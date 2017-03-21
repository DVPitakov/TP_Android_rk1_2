package com.example.dmitry.android_rk1_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/**
 * Created by dmitry on 21.03.17.
 */

public class ServiceHelper implements AppResultReceiver.Receiver {

    public final static int SUCCESS     = 1;
    public final static int FAIL        = 0;
    public final static String RECEIVER = "RECEIVER";

    interface ServiceHelperListener {
        void onServiceHelperResult();
    }
    private static AppResultReceiver appResultReceiver;
    private ServiceHelperListener listener;
    private static ServiceHelper instance;

    private ServiceHelper() {

    }
    void request(Context context) {
        Log.d("myMsg", "request");
        Intent intent = new Intent(context, MyIntentService.class);
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
        if (listener != null) {
            listener = null;
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        String st = data.getString("ST");
        Log.d("myMsg", "hello");
        Log.d("myMsg", st);
        listener.onServiceHelperResult();
    }
}
