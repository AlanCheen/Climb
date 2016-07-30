package me.yifeiyuan.climb.module.gank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GankFragment extends BaseFragment {

    public static final String TAG = "GankFragment";

    private List<BaseFragment> mFragments;
    private List<String> mTitles;
    private FAdapter mAdapter;

    @Bind(R.id.vp_gank)
    ViewPager mVpGank;

    @Bind(R.id.tbl)
    TabLayout mTabLayout;

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

        mFragments = new ArrayList<>();
        mFragments.add(AndFragment.newInstance());
        mFragments.add(MeiziFragment.newInstance());
        mAdapter = new FAdapter(getChildFragmentManager());

        mTitles = new ArrayList<>();
        mTitles.add("Android");
        mTitles.add("妹纸");

        setHasOptionsMenu(true);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        mVpGank.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mVpGank);
    }

    @Override
    protected void requestData(boolean isForce, boolean isRefresh) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.gank_main_menu, menu);
    }

    private class FAdapter extends FragmentPagerAdapter {

        public FAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

    }
}
