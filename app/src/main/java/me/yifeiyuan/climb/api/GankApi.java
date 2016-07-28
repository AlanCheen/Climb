package me.yifeiyuan.climb.api;

import me.yifeiyuan.climb.data.GAndroid;
import me.yifeiyuan.climb.data.GankConfig;
import me.yifeiyuan.climb.data.GankDaily;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * api see: http://gank.io/api
 *
 * Created by 程序亦非猿 (http://weibo.com/alancheeen)
 */
public interface GankApi {

    @GET("data/Android/"+ GankConfig.PAGE_COUNT+"/{page}")
    Observable<GAndroid> getAndroid(@Path("page") int page);

    // http://gank.avosapps.com/api/day/2015/08/06
    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getDaily(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/福利/"+GankConfig.PAGE_COUNT+"/{page}")
    Observable<GAndroid> getMeizi(@Path("page") int page);
}
