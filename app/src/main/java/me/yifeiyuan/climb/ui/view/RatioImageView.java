package me.yifeiyuan.climb.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import me.yifeiyuan.climb.R;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class RatioImageView extends ImageView {

    private float mRatio = 1.0f;

    public RatioImageView(Context context) {
        this(context,null,0);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        if (null != ta) {
            mRatio = ta.getFloat(R.styleable.RatioImageView_ratio, 1.0f);
        }
        ta.recycle();
    }

    public float getRatio() {
        return mRatio;
    }

    public void setRatio(float ratio) {
        mRatio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = (int) (width*mRatio);
        setMeasuredDimension(width, height);
    }
}