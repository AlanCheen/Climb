package me.yifeiyuan.climb.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import me.yifeiyuan.climb.BuildConfig;
import me.yifeiyuan.climb.data.GAndroid;
import me.yifeiyuan.climb.data.GankConfig;
import me.yifeiyuan.climb.data.GankDaily;
import me.yifeiyuan.climb.data.SplashEntity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Stair github:  https://github.com/AlanCheen/Stair
 * Created by 程序亦非猿 (http://weibo.com/alancheeen)
 * on 15/10/8
 */
public class Api {

    private volatile static Api sApi;

    public static Api getIns() {
        if (sApi == null) {
            synchronized (Api.class) {
                if (null == sApi) {
                    sApi = new Api();
                }
            }
        }
        return sApi;
    }

    private final GankApi mGankApi;
    private final ZhiHuApi mZhiHuApi;

    private Api() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30_000, TimeUnit.MILLISECONDS)
                .readTimeout(30_000, TimeUnit.SECONDS)
                .writeTimeout(30_000, TimeUnit.SECONDS);

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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.newThread()))
                .client(mOkHttpClient)
                .build();

        mGankApi = retrofit.create(GankApi.class);

        mZhiHuApi = retrofit.create(ZhiHuApi.class);

    }

    public  Observable<GAndroid> getAndroid(int page) {
        return mGankApi.getAndroid(page);
    }

    public Observable<GAndroid> getMeizi(int page){
        return mGankApi.getMeizi(page);
    }

   public Observable<GankDaily> getDaily(int year, int month, int day){
       return mGankApi.getDaily(year,month,day);
   }

    public Observable<SplashEntity> getWel() {
        return mZhiHuApi.getWel();
    }
}
