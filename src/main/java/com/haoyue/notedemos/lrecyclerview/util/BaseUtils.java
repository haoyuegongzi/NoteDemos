package com.haoyue.notedemos.lrecyclerview.util;

import com.haoyue.notedemos.app_base.BaseApplication;
import com.haoyue.notedemos.retrofit.mvp.ClientApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：chen1 on 2018/3/19 10
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class BaseUtils {
    public static Retrofit getRetrofit(String baseURL){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(BaseApplication.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


}
