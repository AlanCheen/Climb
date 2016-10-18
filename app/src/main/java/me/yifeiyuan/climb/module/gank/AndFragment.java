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

package me.yifeiyuan.climb.module.gank;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

import me.yifeiyuan.climb.base.RefreshFragment;
import me.yifeiyuan.climb.data.GankResponse;
import me.yifeiyuan.climb.data.entity.GankEntity;
import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AndFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AndFragment extends RefreshFragment<GankAdapter> {

    public static final String TAG = "AndFragment";

    private GankAdapter mAdapter;
    private ArrayList<GankEntity> mDatas;
    private boolean canLoadmore;

    public static AndFragment newInstance() {
        AndFragment fragment = new AndFragment();
        return fragment;
    }

    @Override
    protected void initData() {
        mDatas = new ArrayList<>();
        mAdapter = new GankAdapter(mActivity, mDatas);
    }

    @Override
    protected GankAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected Subscription onRequestData(boolean isForce, boolean isRefresh) {

        return mActivity.getAppComponent().api().getAndroid(mCurrPage)
                .subscribe(new RefreshSubscriber<GankResponse>(isForce,isRefresh) {
                    @Override
                    public void onNext(GankResponse entity) {
                        canLoadmore = entity.results.size() >= 0;

                        if (mCurrPage == 1) {
                            mDatas.clear();
                        } else {
                            setLoadMoreComplete();
                        }
                        mDatas.addAll(entity.results);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
