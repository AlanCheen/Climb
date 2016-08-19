package me.yifeiyuan.climb.base;

/**
 * Created by 程序亦非猿 on 16/6/1 下午6:42.
 * Climb
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V mView;

    @Override
    public void attachView(V v) {
        this.mView = v;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    protected boolean isViewActive() {
        return null != mView && mView.isActive();
    }
}
