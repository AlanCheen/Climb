package me.yifeiyuan.climb.api;


import me.yifeiyuan.climb.data.entity.SplashEntity;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 程序亦非猿 on 16/6/1 下午5:29.
 * Climb
 *
 * https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
 */
public interface ZhiHuApi {


    @GET("http://news-at.zhihu.com/api/4/start-image/1080*1776")
    Observable<SplashEntity> getWel();


    /**主题日报列表*/
    @GET("http://news-at.zhihu.com/api/4/themes")
    Observable<RequestBody> themeList();

    /**主题日报内容*/
    @GET("http://news-at.zhihu.com/api/4/theme/{themeId}")
    Observable<RequestBody> themeDetails(@Path("themeId") int themeId);
}
