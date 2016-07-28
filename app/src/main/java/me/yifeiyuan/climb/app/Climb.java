package me.yifeiyuan.climb.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by 程序亦非猿 on 16/5/31 下午6:53.
 * Climb
 */

public class Climb extends Application{

    private static final String TAG = "Climb";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: "+getPackageName());
    }
}
