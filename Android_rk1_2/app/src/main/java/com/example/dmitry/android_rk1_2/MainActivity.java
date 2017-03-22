package com.example.dmitry.android_rk1_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ServiceHelper.ServiceHelperListener {

    Button updateButton;
    Button settingsButton;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Storage.getInstance(this);



        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        updateButton = (Button)findViewById(R.id.updateButton);
        settingsButton = (Button)findViewById(R.id.settingsButton);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceHelper.getInstance().sendRequest(MainActivity.this);
            }
        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ServiceHelper.getInstance().setListener(this);
        ServiceHelper.getInstance().sendRequest(MainActivity.this);
    }
    @Override
    protected void onDestroy() {
        ServiceHelper.getInstance().removeListener();
        super.onDestroy();
    }

    @Override
    public void onServiceHelperResult(Bundle data) {
        textView1.setText(data.getString(Cource.COURCE_VALUE)
                + data.getString(Cource.COURCE_CURRENCY));
        int i = data.getInt(Cource.COURCE_STATUS);
        String status = "ERROR";
        switch (i) {
            case Cource.ERROR: status = "ERROR"; break;
            case Cource.FROM_CACHE: status = "FROM CACHE"; break;
            case Cource.OK: status = "OK"; break;
        }
        textView2.setText(status);
    }
}
