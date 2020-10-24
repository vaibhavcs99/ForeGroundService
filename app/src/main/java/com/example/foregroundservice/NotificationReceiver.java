package com.example.foregroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent2 = new Intent("com.example.foregroundservice");
        context.sendBroadcast(intent2);
        Toast.makeText(context, "Finallyyyy worked!", Toast.LENGTH_SHORT).show();

    }
}
