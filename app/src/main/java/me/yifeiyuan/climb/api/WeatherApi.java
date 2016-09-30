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

import me.yifeiyuan.climb.app.Config;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 程序亦非猿 on 16/9/29.
 *
 * http://apistore.baidu.com/apiworks/servicedetail/478.html
 *
 * http://www.heweather.com/documents/
 */
public interface WeatherApi {

    //  https://api.heweather.com/x3/weather?cityid=城市ID&key=你的认证key
//    @GET("https://api.heweather.com/x3/weather?")
//    Observable<RequestBody> getWeather(@Query("cityid") String city, @Query("key")String key);

    @GET("http://apis.baidu.com/heweather/weather/free")
    @Headers({"apikey:"+ Config.BAIDU_WEATHER})
    Observable<RequestBody> getWeather(@Query("city") String city);
}
