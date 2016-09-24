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
import me.yifeiyuan.climb.tools.trace.Agent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GankFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

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
        mFragments.add(IosFragment.newInstance());
        mAdapter = new FAdapter(getChildFragmentManager());

        mTitles = new ArrayList<>();
        mTitles.add("Android");
        mTitles.add("妹纸");
        mTitles.add("iOS");

        setHasOptionsMenu(true);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        mVpGank.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mVpGank);
        mVpGank.addOnPageChangeListener(this);
    }

    @Override
    protected void requestData(boolean isForce, boolean isRefresh) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.gank_main_menu, menu);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Agent.onEvent(mActivity,"gank_"+mAdapter.getPageTitle(position).toString());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
