/*
 * Copyright (C) 2015, 程序亦非猿
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

package me.yifeiyuan.climb.ui.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 程序亦非猿 on 16/7/14.
 */
public class T {

    private static Toast sToast ;

    public static void show(Context cxt, String content) {
        if (null != sToast) {
            sToast.cancel();
        }
        sToast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT);
        sToast.show();
    }
}
