package me.yifeiyuan.climb.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class OPRecyclerView extends RecyclerView implements OPScrollListener.OnBottomListener {

    private static final String TAG = "OPRecyclerView";


    private  OPScrollListener mOPScrollListener;
    public OPRecyclerView(Context context) {
        super(context);
        init();
    }

    public OPRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OPRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (null == mOPScrollListener) {
            mOPScrollListener = new OPScrollListener(layout, this);
            addOnScrollListener(mOPScrollListener);
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
        if (null != mOPScrollListener) {
            mOPScrollListener.setLoadMoreComplete();
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