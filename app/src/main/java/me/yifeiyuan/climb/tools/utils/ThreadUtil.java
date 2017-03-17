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

package me.yifeiyuan.climb.tools.utils;

import android.os.Looper;

/**
 * Created by 程序亦非猿 on 17/2/4.
 *
 * 线程相关工具类
 */
public class ThreadUtil {

    private ThreadUtil() {
        //no instance
        throw new AssertionError("No instances.");
    }

    /**
     * @return true if called on the main thread,false otherwise.
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * @return true if called on a background thread, false otherwise.
     */
    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }

    /**
     * Throws an {@link java.lang.IllegalArgumentException} if called on a thread other than the main thread.
     */
    public static void assertMainThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /**
     * Throws an {@link java.lang.IllegalArgumentException} if called on the main thread.
     */
    public static void assertBackgroundThread() {
        if (!isOnBackgroundThread()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        }
    }
}
