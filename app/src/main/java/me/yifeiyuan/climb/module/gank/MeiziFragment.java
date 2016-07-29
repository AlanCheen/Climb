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

package me.yifeiyuan.climb.module.gank;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.yifeiyuan.climb.api.Api;
import me.yifeiyuan.climb.base.RefreshFragment;
import me.yifeiyuan.climb.data.GAndroid;
import me.yifeiyuan.climb.data.GankEntity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class MeiziFragment extends RefreshFragment<GankMeiziAdapter> {

    public static final String TAG = "MeiziFragment";

    private GankMeiziAdapter mAdapter;
    private ArrayList<GankEntity> mDatas;
    private boolean canLoadmore;

    public static MeiziFragment newInstance() {
        MeiziFragment fragment = new MeiziFragment();
        return fragment;
    }

    @Override
    protected void initData() {
        mDatas = new ArrayList<>();
        mAdapter = new GankMeiziAdapter(mActivity, mDatas);
    }

    @Override
    protected GankMeiziAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected void onRequestData(boolean isForce, boolean isRefresh) {

        Observable<GAndroid> android = Api.getIns().getMeizi(mCurrPage);
        android.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RefreshSubscriber<GAndroid>(isForce,isRefresh){
                    @Override
                    public void onNext(GAndroid gAndroid) {
                        List<GankEntity> entities = gAndroid.results;
                        canLoadmore = entities.size() >= 0;
                        if (mCurrPage == 1) {
                            mDatas.clear();
                        }else {
                            setLoadMoreComplete();
                        }
                        mDatas.addAll(entities);
                        mAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onNext() called with: " + "gAndroid = [" + gAndroid + "]");
                    }
                });
    }
}
