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

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;


/**
 *  Created by 程序亦非猿 on 16/5/31.
 */
public abstract class BaseFragment extends Fragment {

    protected static String TAG = "BaseFragment";

    protected View mRootView;

    public Activity mActivity;
    protected CompositeSubscription mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        mActivity = getActivity();
        mSubscription = new CompositeSubscription();
        Bundle args = getArguments();
        if (null != args) {
            initArguments(args);
        }
        init(savedInstanceState);
        requestData(false, true);
        return mRootView;
    }

    protected abstract @LayoutRes int getLayoutId();

    protected void initArguments(@NonNull Bundle args){}

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract void requestData(boolean isForce, boolean isRefresh);

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
        if (null!=mSubscription&&!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }


    protected final <T> T find(@IdRes int id){
        return (T) mRootView.findViewById(id);
    }
}

