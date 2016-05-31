package me.yifeiyuan.climb.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


/**
 *  Created by 程序亦非猿 on 16/5/31.
 */
public abstract class BaseFragment extends Fragment {

    protected static String TAG = "BaseFragment";

    protected View mRootView;

    public Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        mActivity = getActivity();
        return mRootView;
    }

    protected abstract @LayoutRes int provideLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }

    protected abstract void init(@Nullable Bundle savedInstanceState);

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG); //统计页面
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

