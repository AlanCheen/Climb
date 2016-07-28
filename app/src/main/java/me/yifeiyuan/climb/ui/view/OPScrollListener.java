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

import android.support.v7.widget.RecyclerView;

import static me.yifeiyuan.climb.tools.Checker.nonNull;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class OPScrollListener extends RecyclerView.OnScrollListener {

    private boolean isLoading;

    BottomDetector mBottomDetector;

    public OPScrollListener(RecyclerView.LayoutManager lm, OnBottomListener listener) {
        mBottomDetector = BottomDetectorFactory.create(lm);
        mOnLoadmoreListener = nonNull(listener);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        // 如果不是上拉 就return
        if (dy <= 0 || isLoading) {
            return;
        }

        if (mBottomDetector.isBottom()) {
            isLoading = true;
            if (null != mOnLoadmoreListener) {
                mOnLoadmoreListener.onBottom();
            }
        }
    }

    private OnBottomListener mOnLoadmoreListener;

    public void setLoadMoreComplete() {
        isLoading = false;
    }

    interface OnBottomListener {
        void onBottom();
    }
}
