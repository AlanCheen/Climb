package me.yifeiyuan.climb.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.umeng.analytics.AnalyticsConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import me.yifeiyuan.climb.tools.utils.ChannelUtil;

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


    @Override
    protected void attachBaseContext(Context base) {
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread", false, base.getClassLoader());
            Method m = activityThread.getDeclaredMethod("currentActivityThread");
            m.setAccessible(true);
            Object ah = m.invoke(null);
            Log.d("Climb", "onNavigationItemSelected: "+ah.toString());

            Method getPm = activityThread.getDeclaredMethod("getPackageManager");
            getPm.setAccessible(true);
            Object pkgManager = getPm.invoke(ah);

            Class<?> pmClazz = Class.forName("android.content.pm.IPackageManager", false, base.getClassLoader());

            Object pmProxy = Proxy.newProxyInstance(base.getClassLoader(), new Class[]{pmClazz}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    //拦截getApplicationInfo方法
                    if ("getApplicationInfo".equals(method.getName())) {

                        int mask = (int) args[1];
                        Object result = method.invoke(pkgManager, args);
                        if (mask == PackageManager.GET_META_DATA) {
                            ApplicationInfo applicationInfo = (ApplicationInfo) result;
                            if (applicationInfo.metaData == null) {
                                applicationInfo.metaData = new Bundle();
                            }
                            //把UMENG_CHANNEL这个key都是替换成我们自己的
                            applicationInfo.metaData.putString("UMENG_CHANNEL", "TEst:"+ ChannelUtil.getChannel(base));
                        }
                        return result;
                    }

                    //其它的方法就让它自己过去吧
                    return method.invoke(pkgManager, args);
                }
            });

            Field packageManagerField = activityThread.getDeclaredField("sPackageManager");
            packageManagerField.setAccessible(true);
            packageManagerField.set(ah, pmProxy);

            String c = AnalyticsConfig.getChannel(this);

            ApplicationInfo appInfo = base.getPackageManager().getApplicationInfo(base.getPackageName(), PackageManager.GET_META_DATA);
            String channel = String.valueOf(appInfo.metaData.get("UMENG_CHANNEL"));

            Log.d("Channel after", "onNavigationItemSelected: "+c+";;;;"+channel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        super.attachBaseContext(base);
    }
}
