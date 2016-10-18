package me.yifeiyuan.climb.network.api;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import me.yifeiyuan.climb.data.GankDaily;
import me.yifeiyuan.climb.data.GankResponse;
import me.yifeiyuan.climb.data.RxResponse;
import me.yifeiyuan.climb.data.entity.SplashEntity;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 程序亦非猿 on 16/7/1.
 */
public class Api {

    private final GankApi mGankApi;
    private final ZhiHuApi mZhiHuApi;
    private final WeatherApi mWeatherApi;

    @Inject
    protected Api(GankApi gankApi,ZhiHuApi zhiHuApi,WeatherApi weatherApi) {
        mGankApi = gankApi;
        mZhiHuApi = zhiHuApi;
        mWeatherApi = weatherApi;
    }

    // TODO: 16/7/29 compose

    public Observable<GankResponse> getAndroid(int page) {
        return wrap(mGankApi.getAndroid(page));
    }

    public Observable<GankResponse> getIos(int page) {
        return wrap(mGankApi.getIos(page));
    }

//    public Observable<List<GIosEntity>> getIos(int page) {
//        return apply(mGankApi.getIos(page));
//    }

    public Observable<GankResponse> getMeizi(int page) {
        return wrap(mGankApi.getMeizi(page));
    }

    public Observable<GankDaily> getDaily(int year, int month, int day) {
        return mGankApi.getDaily(year, month, day);
    }

    public Observable<SplashEntity> getWel() {
        return wrap(mZhiHuApi.getWel());
    }


    public Observable<RequestBody> getWeather(String city) {
        return wrap(mWeatherApi.getWeather(city));
    }

    private  <T> Observable<T> wrap(Observable<T> o) {
        return o.timeout(10_000,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private <T> Observable<T> apply(Observable<RxResponse<T>> observable) {
        return observable.timeout(10_000, TimeUnit.MILLISECONDS)
                .flatMap(new PickResults<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private class PickResults<T> implements Func1<RxResponse<T>, Observable<T>> {

        @Override
        public Observable<T> call(RxResponse<T> tRxResponse) {
            return Observable.just(tRxResponse.results);
        }
    }
}