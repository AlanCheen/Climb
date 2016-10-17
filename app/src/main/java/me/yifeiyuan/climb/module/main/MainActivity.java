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

package me.yifeiyuan.climb.module.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.AnalyticsConfig;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.network.api.Api;
import me.yifeiyuan.climb.base.BaseActivity;
import me.yifeiyuan.climb.module.gank.GankFragment;
import me.yifeiyuan.climb.tools.bus.OldDriver;
import me.yifeiyuan.climb.tools.utils.ChannelUtil;
import okhttp3.RequestBody;
import rx.Subscriber;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

//    @Bind(R.id.fab)
//    FloatingActionButton mFab;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    ImageView mIvAvatar;
    TextView mTvNick;
    TextView mTvMail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        overridePendingTransition(R.anim.fade_in,R.anim.no_anim);
        OldDriver.getIns().register(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setupDrawer();
        setSwipeBackEnable(false);

        mNavView.setNavigationItemSelectedListener(this);
        mNavView.setItemIconTintList(null);
        View header = mNavView.getHeaderView(0);
        mIvAvatar = (ImageView) header.findViewById(R.id.avatar);
        mTvMail = (TextView) header.findViewById(R.id.mail);
        mTvNick = (TextView) header.findViewById(R.id.nick);
        mTvMail.setOnClickListener(this);

        if (null == savedInstanceState) {
//            addFragment(AndFragment.newInstance(),AndFragment.TAG);
//            addFragment(MeiziFragment.newInstance(),MeiziFragment.TAG);
            addFragment(GankFragment.newInstance(),GankFragment.TAG);
        }
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onResume() {
        super.onResume();
        String channel = AnalyticsConfig.getChannel(this);
        Log.d(TAG, "onResume: "+channel);

        Api.getIns().getWeather("hangzhou").subscribe(new Subscriber<RequestBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RequestBody requestBody) {
                Log.d(TAG, "onNext() called with: requestBody = [" + requestBody + "]");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String channel = ChannelUtil.getChannel(this);

        Log.d("Climb", "onNavigationItemSelected: "+channel);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gank) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        item.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mail:
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setData(Uri.parse("mailto:" + mTvMail.getText()));
                mail.putExtra(Intent.EXTRA_SUBJECT, "来自 Climb");
                mail.putExtra(Intent.EXTRA_TEXT, "我想说:");
                if (mail.resolveActivity(getPackageManager())!= null) {
                    startActivity(mail);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OldDriver.getIns().unregister(this);
    }

    @Subscribe
    public void onOpenDrawer(OpenDrawerEvent event) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
}
