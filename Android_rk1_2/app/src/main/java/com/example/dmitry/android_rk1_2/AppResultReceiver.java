package com.example.dmitry.android_rk1_2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/**
 * Created by dmitry on 21.03.17.
 */

public class AppResultReceiver extends ResultReceiver {

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle data);
    }

    private Receiver receiver;

    public AppResultReceiver(Handler handler) {
        super(handler);
    }
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    /*ServiceHelper object ever exist
    public void removeReciever() {
        this.receiver = null;
    }
    */

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        Log.d("myMsg", "onRecieveResult");
        Log.d("myMsg", "onRecieveResult2");
        receiver.onReceiveResult(resultCode, resultData);
    }
}
