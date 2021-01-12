package com.classy.class_2021a_and_10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.util.Log;

public class LightReceiver extends BroadcastReceiver {

    public static final String EXTRA_LIGHT = "EXTRA_LIGHT";
    public static final String ACTION_LIGHT = "ACTION_LIGHT";

    public interface CallBack_Light {
        void light(float val);
    }

    private CallBack_Light callBack_light;

    public LightReceiver(CallBack_Light callBack_light) {
        this.callBack_light = callBack_light;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        float val = intent.getFloatExtra(EXTRA_LIGHT, 0);
        Log.d("ptttR", "EXTRA_LIGHT = " + val);

        if (callBack_light != null) {
            callBack_light.light(val);
        }
    }
}
