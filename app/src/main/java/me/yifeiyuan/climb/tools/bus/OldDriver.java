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

package me.yifeiyuan.climb.tools.bus;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by 程序亦非猿 on 16/9/29.
 */
public class OldDriver implements IBus {

    private static OldDriver sIns = new OldDriver();

    private EventBus mBus = new EventBus();

    public static OldDriver getIns() {
        return sIns;
    }

    @Override
    public void post(Object event) {
        mBus.post(event);
    }

    @Override
    public void register(Object o) {
        mBus.register(o);
    }

    @Override
    public void unregister(Object o) {
        mBus.unregister(o);
    }
}
