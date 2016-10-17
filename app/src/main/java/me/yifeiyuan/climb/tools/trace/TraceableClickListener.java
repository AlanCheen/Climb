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

import android.support.annotation.CallSuper;
import android.util.Log;
import android.view.View;

/**
 * Created by 程序亦非猿 on 16/9/20.
 *
 * 没有抽象方法,但是定义为抽象是为了方便开发。
 */
public abstract class TraceableClickListener implements View.OnClickListener ,Traceable{

    @CallSuper
    @Override
    public void onClick(View v) {
        trace();
    }

    @Override
    public void trace() {
        if (traceEnable()) {
            Log.d(getClassName(),new Throwable().getStackTrace()[2].toString());
        }
    }

    protected boolean traceEnable() {
        return me.yifeiyuan.climb.BuildConfig.DEBUG;
    }

    private String getClassName() {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        StackTraceElement stack = stacks[2];
        String fullName = stack.getClassName();//com.a.b.XXX 全名
        int index = fullName.lastIndexOf(".");
        String simpleName = fullName.substring(index+1, fullName.length());//取 XXX
        return simpleName;
    }

}
