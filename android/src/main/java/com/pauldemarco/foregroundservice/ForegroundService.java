package com.pauldemarco.foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Build;
import android.graphics.Color;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ForegroundServicePlugin.STARTFOREGROUND_ACTION.equals(intent.getAction())) {
            PackageManager pm = getApplicationContext().getPackageManager();
            Intent notificationIntent = pm.getLaunchIntentForPackage(getApplicationContext().getPackageName());
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Bundle bundle = intent.getExtras();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                int ONGOING_NOTIFICATION_ID = 2;

                String NOTIFICATION_CHANNEL_ID = "com.pauldemarco.foregroundservice";
                String channelName = "Foreground Service";
                NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    channelName, NotificationManager.IMPORTANCE_NONE);

                chan.setLightColor(Color.BLUE);
                chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                NotificationManager manager = (NotificationManager) getSystemService(
                    getApplicationContext().NOTIFICATION_SERVICE);
                assert manager != null;
                manager.createNotificationChannel(chan);

                NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

                Notification notification = notificationBuilder
                    .setOngoing(true)
                    // .setSmallIcon(R.drawable.icon_1)
                    .setContentTitle(bundle.getString("title"))
                    .setContentText(bundle.getString("text"))
                    .setSubText(bundle.getString("subText"))
                    .setTicker(bundle.getString("ticker"))
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();

                startForeground(ONGOING_NOTIFICATION_ID, notification);
            }
            else
            {
                int ONGOING_NOTIFICATION_ID = 1;

                Notification notification =
                    new NotificationCompat.Builder(this)
                            .setOngoing(true)
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setContentTitle(bundle.getString("title"))
                            .setContentText(bundle.getString("text"))
                            .setSubText(bundle.getString("subText"))
                            .setTicker(bundle.getString("ticker"))
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentIntent(pendingIntent)
                            .build();
                            
               startForeground(ONGOING_NOTIFICATION_ID, notification);
            }

        } else if (ForegroundServicePlugin.STOPFOREGROUND_ACTION.equals(intent.getAction())) {
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }
}
