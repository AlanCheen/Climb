package me.yifeiyuan.climb.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import me.yifeiyuan.climb.hook.PackageManagerHook;

/**
 * Created by 程序亦非猿 on 16/5/31 下午6:53.
 * Climb
 */
public class App extends Application{

    private static final String TAG = "Climb";
    @Override
    public void onCreate() {
        super.onCreate();
//        PackageManagerHook.hook(this);
        Log.d(TAG, "onCreate: "+getPackageName());
    }


    @Override
    protected void attachBaseContext(Context base) {
        PackageManagerHook.hook(base);
        super.attachBaseContext(base);
    }
}
