package me.yifeiyuan.climb.module.gank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.yifeiyuan.climb.api.Api;
import me.yifeiyuan.climb.base.RefreshFragment;
import me.yifeiyuan.climb.data.GIosEntity;
import rx.Subscriber;
import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IosFragment extends RefreshFragment {

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

    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return null;
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
        return Api.getIns().getIos(mCurrPage).subscribe(new Subscriber<List<GIosEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GIosEntity> gIosEntities) {

            }
        });
    }

}
