package com.classy.class_2021a_and_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView main_LBL_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LBL_info = findViewById(R.id.main_LBL_info);

        Intent intent = new Intent(this, MyService.class);
        startService(intent);


        LightReceiver lightReceiver = new LightReceiver(new LightReceiver.CallBack_Light() {
            @Override
            public void light(float val) {
                main_LBL_info.setText(val + "\nlum");
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LightReceiver.ACTION_LIGHT);
        registerReceiver(lightReceiver, intentFilter);

    }




}