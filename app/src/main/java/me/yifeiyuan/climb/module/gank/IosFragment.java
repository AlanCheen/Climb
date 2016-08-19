package me.yifeiyuan.climb.module.gank;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import me.yifeiyuan.climb.api.Api;
import me.yifeiyuan.climb.base.RefreshFragment;
import me.yifeiyuan.climb.data.GankResponse;
import me.yifeiyuan.climb.data.entity.GankEntity;
import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IosFragment extends RefreshFragment<GankAdapter> {

    private GankAdapter mAdapter;
    private ArrayList<GankEntity> mDatas;
    private boolean canLoadmore;

    /**
     * @return A new instance of fragment IosFragment.
     */
    public static IosFragment newInstance() {
        IosFragment fragment = new IosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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

    /**
     * http://stackoverflow.com/questions/2770321/what-is-a-raw-type-and-why-shouldnt-we-use-it
     *
     * @param isForce
     * @param isRefresh
     * @return
     */
    @Override
    protected Subscription onRequestData(boolean isForce, boolean isRefresh) {
        return Api.getIns().getIos(mCurrPage)
                .subscribe(new RefreshSubscriber<GankResponse>(isForce, isRefresh) {
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
