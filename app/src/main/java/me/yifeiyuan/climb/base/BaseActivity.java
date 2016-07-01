package me.yifeiyuan.climb.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yifeiyuan.climb.R;


/**
 * Created by 程序亦非猿 on 16/5/31.
 *
 * 统一:
 * 设置toolbar
 * 设置title
 * 处理左上返回按钮事件
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Nullable
    @Bind(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout mCollapsingToolbar;

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Nullable
    @Bind(R.id.appbar)
    protected AppBarLayout mAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        final int layoutId = getLayoutId();
        if (layoutId > 0) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }
        Intent intent = getIntent();
        if (null != intent) {
            initIntent(intent);
        }
        initData();
        initView(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    /**
     * 设置toolbar
     */
    protected void setupToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //统一设置 title
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        updateTitle(title);
    }

    protected void updateTitle(CharSequence title) {
        if (null != mCollapsingToolbar) {
            mCollapsingToolbar.setTitle(title);
        } else if (null != mToolbar) {
            mToolbar.setTitle(title);
        }
    }


    protected abstract @LayoutRes int getLayoutId();

    protected abstract void initData();

    protected void initIntent(@NonNull Intent intent) {}

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 添加fragment
     *
     * @param frag 要添加的frag
     * @param tag  frag的tag
     * @see #getContainerViewId()
     */
    protected void addFragment(@NonNull Fragment frag, @NonNull String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(getContainerViewId(), frag, tag)
                .commitAllowingStateLoss();
    }

    /**
     *
     * @return 存放fragment的container 的ID
     */
    protected @IdRes int getContainerViewId() {
        return R.id.container;
    }

    protected final <T extends View> T find(@IdRes int id) {
        return (T) findViewById(id);
    }

}
