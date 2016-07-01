package me.yifeiyuan.climb.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import me.yifeiyuan.climb.MainActivity;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.base.BaseActivity;
import me.yifeiyuan.climb.data.SplashEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * todo 得找个好看点的splash图片 and Icon
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.iv_img)
    ImageView mIv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        Api.getIns().getWel().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SplashEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SplashEntity welEntity) {
                        Glide.with(mContext).load(welEntity.img).into(mIv);
                    }
                });

        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
            }
        });
    }
}
