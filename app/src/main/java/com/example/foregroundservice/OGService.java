package com.example.foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.foregroundservice.NotiChannel.CHANNEL_MAIN;

public class OGService extends Service {
    private static final String TAG = OGService.class.getSimpleName();
    MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_MAIN)
                .setSmallIcon(R.drawable.ic_test)
                .setContentTitle("Forever Noti")
                .setContentText("This is gonna be here until service ends")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service Stopped");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
