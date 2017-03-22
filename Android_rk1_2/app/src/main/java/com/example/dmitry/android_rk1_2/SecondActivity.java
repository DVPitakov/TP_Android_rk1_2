package com.example.dmitry.android_rk1_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button btn5;
    Button btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn5 = (Button)findViewById(R.id.RUB);
        btn6 = (Button)findViewById(R.id.USD);


        btn5.setEnabled(false);
        btn6.setEnabled(false);

        String str = Storage.getInstance(this).getSettings();

        if (str == null) {
            str = Cource.CURRENCYE_RUB;
            Storage.getInstance(this).saveSettings(Cource.CURRENCYE_RUB);
        }
        View view = findViewById(R.id.secondActivityLayout);

        if(str.equals(Cource.CURRENCYE_RUB)) {
            btn5.setEnabled(false);
            btn6.setEnabled(true);
        }
        else if(str.equals(Cource.CURRENCYE_USD)) {
            btn5.setEnabled(true);
            btn6.setEnabled(false);
        }


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn5.setEnabled(false);
                btn6.setEnabled(true);
                Storage.getInstance(null).saveSettings(Cource.CURRENCYE_RUB);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn5.setEnabled(true);
                btn6.setEnabled(false);
                Storage.getInstance(null).saveSettings(Cource.CURRENCYE_USD);
            }
        });

    }
}
