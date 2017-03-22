package com.example.dmitry.android_rk1_2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

/**
 * Created by dmitry on 21.03.17.
 */

public class AppResultReceiver extends ResultReceiver {

    private Receiver receiver;


    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle data);

    }

    public AppResultReceiver(Handler handler) {
        super(handler);

    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;

    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        receiver.onReceiveResult(resultCode, resultData);

    }

}
