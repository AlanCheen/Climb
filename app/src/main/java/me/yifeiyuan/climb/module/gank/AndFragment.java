package me.yifeiyuan.climb.module.gank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.yifeiyuan.climb.base.RefreshFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AndFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AndFragment extends RefreshFragment {

    public static final String TAG = "AndFragment";

    public static AndFragment newInstance() {
        AndFragment fragment = new AndFragment();
        return fragment;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void requestData(boolean isForce, boolean isRefresh) {

    }
}
