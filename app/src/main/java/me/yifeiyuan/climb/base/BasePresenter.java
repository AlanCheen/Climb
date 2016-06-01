package me.yifeiyuan.climb.base;

import android.support.annotation.NonNull;

/**
 * Created by 程序亦非猿 on 16/5/31.
 */
public interface BasePresenter<T extends BaseView> {

    void attach(@NonNull T v);

    void detach();
}
