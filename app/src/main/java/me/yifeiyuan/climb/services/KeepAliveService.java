package me.yifeiyuan.climb.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class KeepAliveService extends Service {


    private static final int SERVICE_ID = 666;

    public KeepAliveService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18) {
            //API < 18 隐藏Notification上的图标
            startForeground(SERVICE_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, KeepAliveInnerService.class);
            startService(innerIntent);
            startForeground(SERVICE_ID, new Notification());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class KeepAliveInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
