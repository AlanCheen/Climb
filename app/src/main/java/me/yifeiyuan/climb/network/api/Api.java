package me.yifeiyuan.climb.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import me.yifeiyuan.climb.BuildConfig;
import me.yifeiyuan.climb.data.GankConfig;
import me.yifeiyuan.climb.data.GankDaily;
import me.yifeiyuan.climb.data.GankResponse;
import me.yifeiyuan.climb.data.RxResponse;
import me.yifeiyuan.climb.data.entity.SplashEntity;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 程序亦非猿 on 16/7/1.
 */
public class Api {
    private static final String TAG = "Ap";

    public static Api getIns() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    private final GankApi mGankApi;
    private final ZhiHuApi mZhiHuApi;
    private final WeatherApi mWeatherApi;

    private Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30_000, TimeUnit.MILLISECONDS)
                .readTimeout(30_000, TimeUnit.MILLISECONDS)
                .writeTimeout(30_000, TimeUnit.MILLISECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        final OkHttpClient mOkHttpClient = builder.build();

        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")   //("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankConfig.URL_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(mOkHttpClient)
                .build();

        mGankApi = retrofit.create(GankApi.class);
        mZhiHuApi = retrofit.create(ZhiHuApi.class);
        mWeatherApi = retrofit.create(WeatherApi.class);
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