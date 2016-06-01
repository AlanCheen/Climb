package me.yifeiyuan.climb.base;

import android.support.annotation.NonNull;


/**
 * Created by 程序亦非猿 on 16/6/1 下午6:42.
 * Climb
 */

public class AbsBasePresenter implements BasePresenter {

    protected BaseView mView;

    @Override
    public void attach(@NonNull BaseView v) {
        mView = v;
    }

    @Override
    public void detach() {
        mView = null;
    }
}
