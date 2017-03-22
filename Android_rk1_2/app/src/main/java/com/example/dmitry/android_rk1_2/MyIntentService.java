package com.example.dmitry.android_rk1_2;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;
import android.system.ErrnoException;
import android.util.Log;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final ResultReceiver receiver = intent.getParcelableExtra(ServiceHelper.RECEIVER);

            final Bundle data = new Bundle();

            Cource cource = Processor.getInstance().getNewCource();
            data.putString(Cource.COURCE_CURRENCY, cource.currency);
            data.putString(Cource.COURCE_VALUE, cource.value);
            data.putInt(Cource.COURCE_STATUS, cource.status);
            receiver.send(cource.status, data);

        }
    }


}
