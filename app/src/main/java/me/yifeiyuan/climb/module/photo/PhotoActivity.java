package me.yifeiyuan.climb.module.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseActivity;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends BaseActivity {

    @Bind(R.id.photo)
    PhotoView mPhoto;

    PhotoViewAttacher mAttacher;

    private String mUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.photo_activity;
    }

    @Override
    protected void initIntent(@NonNull Intent intent) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mAttacher = new PhotoViewAttacher(mPhoto);
    }
}
