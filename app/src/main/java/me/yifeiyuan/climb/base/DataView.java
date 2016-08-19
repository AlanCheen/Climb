package me.yifeiyuan.climb.base;

/**
 * Created by 程序亦非猿 on 16/6/14.
 *
 */
public interface DataView<T> extends IView {
    /**
     * 显示数据
     * @param t 数据类型
     * @param isRefresh   是否是刷新
     */
    void showData(T t, boolean isRefresh);

    /**
     * 出错
     * @param e the error
     */
    void onError(Throwable e);
}
