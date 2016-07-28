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

package me.yifeiyuan.climb.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import me.yifeiyuan.climb.tools.Checker;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class BottomDetectorFactory {

    public static BottomDetector create(@NonNull RecyclerView.LayoutManager lm) {
        Checker.nonNull(lm);
        if (lm instanceof LinearLayoutManager) {
            return new LinearDetector((LinearLayoutManager) lm);
        } else if (lm instanceof StaggeredGridLayoutManager) {
//            return // TODO: 16/7/28
            throw new UnsupportedOperationException("not supported!");
        } else {
            throw new UnsupportedOperationException("not supported!");
        }
    }
}
