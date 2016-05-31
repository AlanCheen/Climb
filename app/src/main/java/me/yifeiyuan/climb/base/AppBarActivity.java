package me.yifeiyuan.climb.base;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yifeiyuan.climb.R;


/**
 * 有toolbar的 统一继承这个activity
 *
 * 统一:
 * 设置toolbar
 * 设置title
 * 处理左上返回按钮事件
 */
public abstract class AppBarActivity extends BaseActivity {

    @Nullable
    @Bind(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout mCollapsingToolbar;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Nullable
    @Bind(R.id.appbar)
    protected AppBarLayout mAppBar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupToolbar();
    }

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

}
