package me.yifeiyuan.climb.module.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseActivity;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends BaseActivity {

    @Bind(R.id.photo)
    ImageView mPhoto;

    PhotoViewAttacher mAttacher;

    private String mUrl;
    private static final String TAG = "PhotoActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.photo_activity;
    }

    @Override
    protected void initIntent(@NonNull Intent intent) {
        String data = intent.getDataString();
        if (!TextUtils.isEmpty(data)) {
            final int index = data.indexOf("url=");
            mUrl = data.substring(index + 4, data.length());
            Log.d(TAG, "initIntent: " + mUrl);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Glide.with(this).load(mUrl).into(mPhoto);
        mAttacher = new PhotoViewAttacher(mPhoto);
    }
}
