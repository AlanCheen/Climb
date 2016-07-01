package me.yifeiyuan.climb.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 程序亦非猿 on 16/6/22 下午2:00.
 * Climb
 */
public abstract class BaseVH<T> extends RecyclerView.ViewHolder {
    protected Context mContext;
    public BaseVH(View v) {
        super(v);
        mContext = v.getContext();
    }
    public abstract void onBind(T t);
}
