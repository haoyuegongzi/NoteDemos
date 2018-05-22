package com.haoyue.notedemos.retrofit.mvp.moudle;

import com.haoyue.notedemos.retrofit.mvp.beaninfo.DouBanBean;

import retrofit2.Call;

/**
 * 作者：chen1 on 2018/3/8 10
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public interface InterfaceModle {
    void postRequestData();
    void postRequestResult();

    void getRequestData(String baseURL, String requestStyle);//Call<DouBanBean>
}
