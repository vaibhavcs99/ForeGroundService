package com.example.foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static com.example.foregroundservice.NotiChannel.CHANNEL_MAIN;

public class OGService extends Service {
    private static final String TAG = OGService.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;
    MediaPlayer mediaPlayer;
    Notification notification;

    public OGService() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 2, notificationIntent, 0);

//Cancel Button
        Intent cancelIntent = new Intent(this, NotificationReceiver.class);
//        cancelIntent.putExtra("cancelId","cancel");
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this, 3, cancelIntent, 0);


        notification = new NotificationCompat.Builder(this, CHANNEL_MAIN)
                .setSmallIcon(R.drawable.ic_test)
                .setContentTitle("Forever Noti")
                .setContentText("This is gonna be here until service ends")
                .setContentIntent(pendingIntent)
                .setColor(Color.BLUE)
                .setNotificationSilent()
//                .addAction(R.mipmap.ic_launcher,"Start", null)
                .addAction(R.mipmap.ic_launcher, "Cancel", cancelPendingIntent)
                .build();
        startForeground(1, notification);

//Broadcast Receiver
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mediaPlayer.stop();
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("com.example.foregroundservice"));

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service Stopped");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
