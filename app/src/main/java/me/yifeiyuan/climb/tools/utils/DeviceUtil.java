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

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by 程序亦非猿 on 17/1/23.
 */
public class DeviceUtil {

    private DeviceUtil() {
        //no instance
        throw new AssertionError("No instances.");
    }

    /**
     * @param cxt
     * @return imei eg:869*****887
     */
    public static String getIMEI(Context cxt) {

        TelephonyManager telephonyManager = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);

        String imei = telephonyManager.getDeviceId();

        return imei;
    }
}
