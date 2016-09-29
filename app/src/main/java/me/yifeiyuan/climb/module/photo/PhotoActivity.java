/*
 * Copyright (C) 2016, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
