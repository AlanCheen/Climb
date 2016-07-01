package me.yifeiyuan.climb.base;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 程序亦非猿 on 16/6/14.
 *
 * 可以请求数据的Presenter
 */
public abstract class DataPresenter<V extends BaseView> extends AbstractPresenter<V> {

    protected CompositeSubscription mSubscription = new CompositeSubscription();

    protected int mPageNo = 1;

    protected final void unSubscribe() {
        if (mSubscription.isUnsubscribed()) {
            unSubscribe();
        }
    }

    protected final void addSubscription(@NonNull Subscription s) {
        mSubscription.add(s);
    }

    /**
     * 暴露给外界取调用
     *
     * @param isForce   true强制请求数据 false 可以取缓存
     * @param isRefresh true 刷新 false 加载更多
     */
    public final void requestData(boolean isForce, boolean isRefresh) {
        addSubscription(doRequest(isForce, isRefresh));
    }

    /**
     * {@link #requestData(boolean, boolean)}
     *
     * @param isRefresh
     */
    public final void requestData(boolean isRefresh) {
        addSubscription(doRequest(true, isRefresh));
    }

    /**
     * {@link #requestData(boolean, boolean)}
     */
    public final void requestData() {
        addSubscription(doRequest(true, true));
    }

    /**
     * @param isForce   true 强制请求数据 false 可以取缓存
     * @param isRefresh true 刷新 false 加载更多
     * @return
     */
    protected abstract Subscription doRequest(final boolean isForce, final boolean isRefresh);

    @Override
    public void detachView() {
        super.detachView();
        unSubscribe();
    }
}
