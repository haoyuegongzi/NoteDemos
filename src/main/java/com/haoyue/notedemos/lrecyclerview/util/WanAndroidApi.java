package com.haoyue.notedemos.lrecyclerview.util;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：chen1 on 2018/3/19 10
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class WanAndroidApi {

    public interface WanAndroidListClient {
        //http://www.wanandroid.com/article/list/0/json

        @GET("article/list/{id}/json")
        Observable<WanAndroidBean> wanAndroidList(@Path("id")String id);
    }
}
