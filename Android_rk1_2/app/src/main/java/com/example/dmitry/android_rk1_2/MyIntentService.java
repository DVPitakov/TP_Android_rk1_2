package com.example.dmitry.android_rk1_2;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.util.Log;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d("myMsg", "123");
            final ResultReceiver receiver = intent.getParcelableExtra(ServiceHelper.RECEIVER);
            sendResult(receiver);
        }
    }

    void sendResult(ResultReceiver receiver) {
        final Bundle data = new Bundle();
        data.putString("ST", "456789");
        receiver.send(ServiceHelper.SUCCESS, data);
    }
}
