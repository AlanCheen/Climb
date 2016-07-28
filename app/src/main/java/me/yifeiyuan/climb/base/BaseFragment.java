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

package me.yifeiyuan.climb.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.yifeiyuan.climb.tools.trace.Agent;
import rx.subscriptions.CompositeSubscription;


/**
 *  Created by 程序亦非猿 on 16/5/31.
 */
public abstract class BaseFragment extends Fragment {

    protected static String TAG = "BaseFragment";

    protected View mRootView;

    public Activity mActivity;
    protected CompositeSubscription mSubscriptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        mActivity = getActivity();
        mSubscriptions = new CompositeSubscription();
        Bundle args = getArguments();
        if (null != args) {
            initArguments(args);
        }
        initData();
        initView(savedInstanceState);
        requestData(false, true);
        return mRootView;
    }

    protected abstract @LayoutRes int getLayoutId();

    protected void initArguments(@NonNull Bundle args){}

    protected abstract void initData();
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract void requestData(boolean isForce, boolean isRefresh);

    public void onResume() {
        super.onResume();
        Agent.onPageStart(TAG); //统计页面
    }
    public void onPause() {
        super.onPause();
        Agent.onPageEnd(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (null!= mSubscriptions &&!mSubscriptions.isUnsubscribed()) {
            mSubscriptions.unsubscribe();
        }
    }


    protected final <T> T find(@IdRes int id){
        return (T) mRootView.findViewById(id);
    }
}

