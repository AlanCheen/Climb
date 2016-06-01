package me.yifeiyuan.climb.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import me.yifeiyuan.climb.R;


/**
 * Created by 程序亦非猿 on 16/5/31.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final String TAG = "BaseActivity";
    protected Context mContext;

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

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

    protected void addFragment(@NonNull Fragment frag,@NonNull String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(getContainerViewId(), frag, tag)
                .commitAllowingStateLoss();
    }

    protected int getContainerViewId() {
        return R.id.container;
    }
}
