package com.haoyue.notedemos.retrofit.mvp;

import com.haoyue.notedemos.retrofit.mvp.beaninfo.DouBanBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 作者：chen1 on 2018/3/8 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class ClientApi {

    /**https://api.douban.com/v2/book/search?q=小王子&tag=&start=0&count=3**/
    public interface CarInfoService{
        @GET("book/search?q=小王子&tag=&start=0&count=3")
        Call<DouBanBean> onlyRetrofit();

        @GET("book/search")
        Call<DouBanBean> retrofitWithMap(@QueryMap Map<String, String > map);


        @GET("book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3")
        Observable<DouBanBean> retrofitWithRx();

        @GET("book/search")
        Observable<DouBanBean> retrofitWithRxAndMap(@QueryMap Map<String, String> map);
    }
}
