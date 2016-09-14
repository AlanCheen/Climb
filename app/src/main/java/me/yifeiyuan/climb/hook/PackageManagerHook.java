/*
 * Copyright (C) 2016, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.yifeiyuan.climb.hook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import me.yifeiyuan.climb.tools.utils.ChannelUtil;

/**
 * Created by 程序亦非猿 on 16/8/22.
 * hook ActivityThread.sPackageManager
 * @see #hook
 *
 */
public class PackageManagerHook  {

    private static String CHANNEL_KEY = "UMENG_CHANNEL";

    /**
     * 越早 hook 越好，推荐在 attachBaseContext 调用
     * @param context base
     */
    public static void hook(Context context) {

        try {

            // 获取 activityThread
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread", false, context.getClassLoader());
            Method currentActivityThread = activityThreadClazz.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object activityThread = currentActivityThread.invoke(null);

            // 获取 activityThread 的 packageManager
            Method getPackageManager = activityThreadClazz.getDeclaredMethod("getPackageManager");
            getPackageManager.setAccessible(true);
            Object pkgManager = getPackageManager.invoke(activityThread);//IPackageManager$Stub$Proxy

            // 动态代理
            Class<?> packageManagerClazz = Class.forName("android.content.pm.IPackageManager", false, context.getClassLoader());

            Object pmProxy = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{packageManagerClazz}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    Object result = method.invoke(pkgManager, args);

                    //拦截getApplicationInfo方法 umeng 获取 CHANNEL_KEY 就是通过它
                    //其它的方法就让它自己过去吧
                    if ("getApplicationInfo".equals(method.getName())) {
                        int mask = (int) args[1];
                        if (mask == PackageManager.GET_META_DATA) {
                            ApplicationInfo appInfo = (ApplicationInfo) result;
                            if (null != appInfo.metaData&&appInfo.metaData.containsKey(CHANNEL_KEY)) {
                                String channel = String.valueOf(appInfo.metaData.get(CHANNEL_KEY));
                                Log.d("Channel before", "："+channel);
                                String newChannel = ChannelUtil.getChannel(context);
                                //把UMENG_CHANNEL这个key都是替换成我们自己的
                                if (!TextUtils.isEmpty(newChannel)) {
                                    appInfo.metaData.putString(CHANNEL_KEY,newChannel);
                                }
                            }
                        }
                    }
                    return result;
                }
            });

            //hook sPackageManager
            Field packageManagerField = activityThreadClazz.getDeclaredField("sPackageManager");
            packageManagerField.setAccessible(true);
            packageManagerField.set(activityThread, pmProxy);

            // test
//            String c = AnalyticsConfig.getChannel(context);
//            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
//            String channel = String.valueOf(appInfo.metaData.get("UMENG_CHANNEL"));
//            Log.d("Channel after", "onNavigationItemSelected: "+c+";;;;"+channel);
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
        }
//        catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
