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

package me.yifeiyuan.climb.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseActivity;
import me.yifeiyuan.climb.base.BaseSubscriber;
import me.yifeiyuan.climb.data.entity.SplashEntity;
import me.yifeiyuan.climb.module.main.MainActivity;
import rx.Observable;

/**
 * todo 得找个好看点的splash图片 and Icon
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.iv_img)
    ImageView mIv;

    @Bind(R.id.tv_txt)
    TextView mTvTxt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        getAppComponent().api().getWel()
                .subscribe(new BaseSubscriber<SplashEntity>() {
                    @Override
                    public void onNext(SplashEntity welEntity) {
                        Glide.with(mContext).load(welEntity.img).into(mIv);
                        mTvTxt.setText(welEntity.text);
                    }
                });

        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribe(new BaseSubscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        go2Main();
                    }
                });
    }

    private void go2Main() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
