package me.yifeiyuan.climb.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class OpRecyclerView extends RecyclerView implements OpOnScrollListener.OnBottomListener {

    private static final String TAG = "OPRecyclerView";


    private OpOnScrollListener mOpOnScrollListener;
    public OpRecyclerView(Context context) {
        super(context);
        init();
    }

    public OpRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (null == mOpOnScrollListener) {
            mOpOnScrollListener = new OpOnScrollListener(layout, this);
            addOnScrollListener(mOpOnScrollListener);
        }
        super.setLayoutManager(layout);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        // TODO: 16/7/28 wrap the adapter
        super.setAdapter(adapter);
    }

    @Override
    public void onBottom() {
        Log.d(TAG, "onBottom: ");
        if (null != mOnLoadMoreListener) {
            mOnLoadMoreListener.onLoadMore();
        }
    }


    public void setLoadMoreComplete() {
        if (null != mOpOnScrollListener) {
            mOpOnScrollListener.setLoadMoreComplete();
        }
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}