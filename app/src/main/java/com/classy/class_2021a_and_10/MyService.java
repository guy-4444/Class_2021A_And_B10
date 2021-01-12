package com.classy.class_2021a_and_10;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    @Override
    public void onCreate() {
        Log.d("pttt", "onCreate Thread: " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("pttt", "onStartCommand Thread: " + Thread.currentThread().getName());
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                longOperation();
            }
        }).start();

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        mySensorManager.registerListener(
                sensorEventListener,
                mySensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            Log.d("pttt", "V = " + value);

            createBroadcastReceiver(value);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    };

    private void createBroadcastReceiver(float val) {
        Intent intent = new Intent(LightReceiver.ACTION_LIGHT);
        intent.putExtra(LightReceiver.EXTRA_LIGHT, val);
        sendBroadcast(intent);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("pttt", "onDestroy");
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    private void longOperation() {
        Log.d("pttt", "longOperation " + Thread.currentThread().getName());
        Log.d("pttt", "A");
        int y = 0;
        for (int i = 0; i < 100000; i++) {
            y = 0;
            for (int j = 0; j < 20000; j++) {
                int x = j;
                y = 3 + x;
            }
        }
        Log.d("pttt", "B");
    }
}
