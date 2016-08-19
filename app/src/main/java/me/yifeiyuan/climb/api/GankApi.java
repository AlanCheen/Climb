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
package me.yifeiyuan.climb.api;

import me.yifeiyuan.climb.data.GankConfig;
import me.yifeiyuan.climb.data.GankDaily;
import me.yifeiyuan.climb.data.GankResponse;
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
    Observable<GankResponse> getAndroid(@Path("page") int page);

    @GET("data/iOS/"+ GankConfig.PAGE_COUNT+"/{page}")
    Observable<GankResponse> getIos(@Path("page") int page);

    @GET("data/all/"+ GankConfig.PAGE_COUNT+"/{page}")
    Observable<GankResponse> getAll(@Path("page") int page);

    // http://gank.avosapps.com/api/day/2015/08/06
    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getDaily(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/福利/"+GankConfig.MEIZI_COUNT+"/{page}")
    Observable<GankResponse> getMeizi(@Path("page") int page);
}
