package me.yifeiyuan.climb.module.gank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import me.yifeiyuan.climb.base.RefreshFragment;

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

    @Override
    protected void onRequestData(boolean isForce, boolean isRefresh) {

    }

}
