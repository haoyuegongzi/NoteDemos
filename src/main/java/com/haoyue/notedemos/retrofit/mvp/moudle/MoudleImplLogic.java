package com.haoyue.notedemos.retrofit.mvp.moudle;

import android.util.Log;

import com.haoyue.notedemos.app_base.BaseApplication;
import com.haoyue.notedemos.retrofit.mvp.ClientApi;
import com.haoyue.notedemos.retrofit.mvp.beaninfo.DouBanBean;
import com.haoyue.notedemos.retrofit.mvp.beaninfo.EventBusMsg;
import com.haoyue.notedemos.retrofit_learn.CarBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：chen1 on 2018/3/8 10
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class MoudleImplLogic implements InterfaceModle{

    Disposable disposable;
//    Call<DouBanBean> call;
    int requestCode = -1;

    @Override
    public void postRequestData() {

    }

    @Override
    public void postRequestResult() {

    }

    @Override
    public  void getRequestData(String baseURL, String requestStyle) {
        switch (requestStyle){
            case "GetRetrofit":
                requestOnlyRetrofit(baseURL);
//                call = requestOnlyRetrofit(baseURL);
                break;
            case "GetRetrofitWithMap":
//                requestRetrofitWithMap(baseURL);
                break;
            case "GetRetrofitAndRxJavaWithMap":
//                requestRetrofitAndRxJavaWithMap(baseURL);
                break;
            case "GetRetrofitAndRxJava":
//                requestRetrofitAndRxJava(baseURL);
                break;
            default:
            break;
        }
    }

    private void requestOnlyRetrofit(String baseURL){
        ClientApi.CarInfoService carInfoObj = getCarInfoObj(baseURL);
        Call<DouBanBean> call = carInfoObj.onlyRetrofit();
        call.enqueue(new Callback<DouBanBean>() {
            @Override
            public void onResponse(Call<DouBanBean> call, Response<DouBanBean> response) {
                if (response != null) {
                    requestCode = 0;
                    DouBanBean douban = response.body();
                    Log.i("TAG", "onResponse: douban ===================" + douban.toString() + "\n\n" +
                                douban.books.toString() + "\n\n" +
                                "douban.books.size() ========" + douban.books.size() );
                    EventBus.getDefault().post(new EventBusMsg("MvpAndRxSerieActivity", requestCode, douban));
                }else {
                    requestCode = 1;
                    EventBus.getDefault().post(new EventBusMsg("MvpAndRxSerieActivity", requestCode, null));
                }
            }

            @Override
            public void onFailure(Call<DouBanBean> call, Throwable t) {
                requestCode = 2;
                EventBus.getDefault().post(new EventBusMsg("MvpAndRxSerieActivity", requestCode, null));
            }
        });
//        return call;
    }

//    private void requestRetrofitWithMap(String baseURL){
//        ClientApi.CarInfoService carInfoObj = getCarInfoObj(baseURL);
//        Map<String, String> parmsCar = parmsCar();
//        Call<CarBean> call = carInfoObj.retrofitWithMap(parmsCar);
//        call.enqueue(new Callback<CarBean>() {
//            @Override
//            public void onResponse(Call<CarBean> call, Response<CarBean> response) {
//                if (response != null) {
//                    requestState = 0;
//                    carInfo = response.body();
//                    Log.i("TAG", "carInfo →→→" + carInfo.toString() + "\n \n" +
//                            "carInfo.translation.size() ===" + carInfo.translation.size() + "\n \n" +
//                            "carInfo.web.size() ===" + carInfo.web.size());
//                }else {
//                    requestState = 1;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CarBean> call, Throwable t) {
//                requestState = 2;
//                Log.i("TAG", "onFailure: 错误的原因： " + t);
//            }
//        });
//    }

//    private void requestRetrofitAndRxJavaWithMap(String baseURL){
//        ClientApi.CarInfoService carInfoObj = getCarInfoObj(baseURL);
//        Map<String, String> parmsCar = parmsCar();
//        disposable = carInfoObj.retrofitWithRxAndMap(parmsCar)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<CarBean>() {
//                    @Override
//                    public void accept(@NonNull CarBean carBean) throws Exception {
//                        if (carBean != null) {
//                            requestCode = 0;
//                            carInfo = carBean;
//                            Log.i("TAG", "carInfo →→→" + carBean.toString() + "\n \n" +
//                                    "carInfo.translation.size() ===" + carBean.translation.size() + "\n \n" +
//                                    "carInfo.web.size() ===" + carBean.web.size());
//                        }else {
//                            requestCode = 2;
//                        }
//                    }
//                });
//    }

//    private void requestRetrofitAndRxJava(String baseURL){
//        ClientApi.CarInfoService carInfoService = getCarInfoObj(baseURL);
//        disposable = carInfoService.retrofitWithRx()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<CarBean>() {
//                    @Override
//                    public void accept(@NonNull CarBean carBean) throws Exception {
//                        if (carBean != null) {
//                            requestState = 0;
//                            carInfo = carBean;
//                            Log.i("TAG", "carInfo →→→" + carInfo.toString() + "\n \n" +
//                                    "carInfo.translation.size() ===" + carInfo.translation.size() + "\n \n" +
//                                    "carInfo.web.size() ===" + carInfo.web.size());
//                        }else {
//                            requestState = 2;
//                        }
//                    }
//                });
//    }

    // 开发中，参数最好还是从View层传过来
    // 因为很多参数都是用户与设备交互过程中动态产生的
    //https://api.douban.com/v2/book/search?
    // q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3

    public Map<String, String> parmsCar(){
        Map<String, String> parms = new HashMap<>();
        parms.put("q", "小王子");
        parms.put("tag", "");
        parms.put("start", "0");
        parms.put("count", "3");
        return parms;
    }

    public ClientApi.CarInfoService getCarInfoObj(String baseURL){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(BaseApplication.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ClientApi.CarInfoService carInfoService = retrofit.create(ClientApi.CarInfoService.class);
        return carInfoService;
    }
}
