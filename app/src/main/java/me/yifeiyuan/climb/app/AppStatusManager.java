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

package me.yifeiyuan.climb.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import me.yifeiyuan.climb.tools.trace.Agent;

/**
 * Created by 程序亦非猿 on 16/12/20.
 */
public class AppStatusManager implements Application.ActivityLifecycleCallbacks {


    private static final String TAG = "AppStatusManager";

    private int activityCount;

    /**
     * 是否在前台
     */
    private boolean isForeground;

    private AppStatusManager() {
    }

    public void init(Application app) {
        app.registerActivityLifecycleCallbacks(this);
    }

    private static class Holder {
        private static final AppStatusManager INSTANCE = new AppStatusManager();
    }

    public static AppStatusManager getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityCount++;
        isForeground = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Agent.onResume(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Agent.onPause(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCount--;
        if (activityCount == 0) {
            isForeground = false;
            Log.d(TAG, "onActivityStopped: App进入后台");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * @return App 是否在前台
     * @see #isAppBackground()
     */
    public boolean isAppForeground() {
        return isForeground;
    }

    /**
     * @return App 是否在后台
     * @see #isAppForeground()
     */
    public boolean isAppBackground() {
        return !isForeground;
    }

    public interface Callback{
        void onAppBackground();
    }
}
