package me.yifeiyuan.climb.base;

import android.os.Bundle;

public abstract class MvpActivity<P extends IPresenter> extends BaseActivity implements IView {

    private P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    protected abstract P getPresenter();

}
