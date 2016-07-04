package me.yifeiyuan.climb.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import me.yifeiyuan.climb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class RefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.col)
    CoordinatorLayout mCol;

    @Override
    protected int getLayoutId() {
        return R.layout.base_refresh_fragment;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mRefresh.setOnRefreshListener(this);
        mRefresh.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent);
    }

    @Override
    public void onRefresh() {
        requestData(true, true);
    }
}
