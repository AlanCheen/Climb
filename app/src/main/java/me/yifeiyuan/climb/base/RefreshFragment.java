package me.yifeiyuan.climb.base;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import me.yifeiyuan.climb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class RefreshFragment<A extends RecyclerView.Adapter> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.rv)
    protected RecyclerView mRv;

    @Bind(R.id.refresh)
    protected SwipeRefreshLayout mRefresh;

    @Nullable
    @Bind(R.id.fab)
    protected FloatingActionButton mFab;

    @Nullable
    @Bind(R.id.col)
    protected CoordinatorLayout mCol;

    @Override
    protected int getLayoutId() {
        return R.layout.base_refresh_fragment;
    }

    @CallSuper
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mRefresh.setColorSchemeColors(R.color.colorAccent);
        mRefresh.setOnRefreshListener(this);
        mRv.setLayoutManager(getLayoutManager());
        mRv.setAdapter(getAdapter());
    }

    @Override
    public void onRefresh() {
        requestData(true, true);
    }

    protected void setRefreshing(boolean refreshing) {
        mRefresh.setRefreshing(refreshing);
    }

    protected abstract A getAdapter();

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
    }
}
