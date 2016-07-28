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
package me.yifeiyuan.climb.tools.trace;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class Agent {

    public static void onEvent(Context context, String eventName, String s1) {
        if (enable()) {
            MobclickAgent.onEvent(context, eventName, s1);
        }
    }

    public static void onEvent(Context context, String eventName) {
        if (enable()) {
            MobclickAgent.onEvent(context, eventName);
        }
    }


    public static void onResume(Context context) {
        if (enable()) {
            MobclickAgent.onResume(context);
        }
    }

    public static void onPause(Context context) {
        if (enable()) {
            MobclickAgent.onPause(context);
        }
    }

    public static void onPageStart(String s) {
        if (enable()) {
            MobclickAgent.onPageStart(s);
        }
    }

    public static void onPageEnd(String s) {
        if (enable()) {
            MobclickAgent.onPageEnd(s);
        }
    }

    private static boolean enable() {
        // 控制开关
//        return !BuildConfig.DEBUG;
        return true;
    }
}
