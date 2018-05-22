package com.haoyue.notedemos.lrecyclerview.moudle;

import com.haoyue.notedemos.lrecyclerview.util.BaseUtils;
import com.haoyue.notedemos.lrecyclerview.util.InterfaceCallBack;
import com.haoyue.notedemos.lrecyclerview.util.WanAndroidApi;
import com.haoyue.notedemos.lrecyclerview.util.WanAndroidBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 作者：chen1 on 2018/3/19 10
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class LRecyclerViewMoudleLogic implements LRecyclerViewMoudleInterface {

    int requestCode = -1;
    @Override
    public void getWanAndroidList(String baseUrl, String id, final InterfaceCallBack wanAndroidListCallBack) {
        Retrofit wanAndroidListRetrofit = BaseUtils.getRetrofit(baseUrl);

        WanAndroidApi.WanAndroidListClient WanAndroidList = wanAndroidListRetrofit.create(WanAndroidApi.WanAndroidListClient.class);
        Disposable disposable = WanAndroidList.wanAndroidList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WanAndroidBean>() {
                    @Override
                    public void accept(WanAndroidBean wanAndroidBean) throws Exception {
                        if (wanAndroidBean == null) {
                            //数据请求成功
                            requestCode = 1;
                        }else {
                            //数据请求失败
                            requestCode = 0;
                        }
                        wanAndroidListCallBack.callBackWanAndroidList(wanAndroidBean, requestCode);
                    }
                });

    }
}
