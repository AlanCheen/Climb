package me.yifeiyuan.climb.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

import me.yifeiyuan.climb.BuildConfig;
import me.yifeiyuan.climb.di.components.AppComponent;
import me.yifeiyuan.climb.di.components.DaggerAppComponent;
import me.yifeiyuan.climb.di.modules.ApiModule;
import me.yifeiyuan.climb.di.modules.AppModule;
import me.yifeiyuan.climb.hook.PackageManagerHook;

/**
 * Created by 程序亦非猿 on 16/5/31 下午6:53.
 * Climb
 */
public class App extends Application {

    private AppComponent mAppComponent;

    private static final String TAG = "Climb";

    @Override
    public void onCreate() {
        super.onCreate();
//        PackageManagerHook.hook(this);
        Log.d(TAG, "onCreate: " + getPackageName());

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();

        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this);
        }

        AppStatusManager.getInstance().init(this);

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        PackageManagerHook.hook(base);
        super.attachBaseContext(base);
    }
}
