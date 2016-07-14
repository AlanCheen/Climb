package me.yifeiyuan.climb.module.gank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GankFragment extends BaseFragment {

    public static final String TAG = "GankFragment";
    public GankFragment() {
        // Required empty public constructor
    }

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void requestData(boolean isForce, boolean isRefresh) {

    }

}
