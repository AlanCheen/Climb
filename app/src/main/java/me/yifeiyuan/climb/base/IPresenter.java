package me.yifeiyuan.climb.base;

import android.support.annotation.NonNull;

/**
 * Created by 程序亦非猿 on 16/5/31.
 */
public interface IPresenter<T extends IView> {

    void attachView(@NonNull T v);

    void detachView();
}
