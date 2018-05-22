package com.haoyue.notedemos.retrofit_learn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.app_base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import retrofit2.http.Headers;

/**
 * @author chen1
 */
public class RetrofitActivity extends BaseActivity {

    String Url = "https://api.github.com/";
    String url = "https://ip.taobao.com/service/getIpInfo.php/";
    String baseUrl = "https://api.douban.com/v2/";
    Disposable disposable;

    @BindView(R.id.tvReTrofitOne)
    TextView tvReTrofitOne;
    @BindView(R.id.tvReTrofitTwo)
    TextView tvReTrofitTwo;
    @BindView(R.id.tvReTrofitThree)
    TextView tvReTrofitThree;
    @BindView(R.id.tvQuerrycontent)
    TextView tvQuerrycontent;
    @BindView(R.id.tvRetrofitWithRxJava)
    TextView tvRetrofitWithRxJava;
    @BindView(R.id.tvRetrofitAndMap)
    TextView tvRetrofitAndMap;
    @BindView(R.id.tvRetrofitWithRxJavaAndMap)
    TextView tvRetrofitWithRxJavaAndMap;
    @BindView(R.id.tvCarInfoRetrofit)
    TextView tvCarInfoRetrofit;
    @BindView(R.id.tvOther)
    TextView tvOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tvReTrofitOne, R.id.tvReTrofitTwo, R.id.tvReTrofitThree, R.id.tvRetrofitWithRxJava,
              R.id.tvRetrofitAndMap, R.id.tvRetrofitWithRxJavaAndMap, R.id.tvCarInfoRetrofit, R.id.tvOther})
    public void onViewClicked(View view) {
        String baseCasrUrl = "http://fanyi.youdao.com/";
        switch (view.getId()) {
            case R.id.tvReTrofitOne:
                requestAutorInfoOne();
                break;
            case R.id.tvReTrofitTwo:
                requestAutorInfoTwo();
                break;
            case R.id.tvReTrofitThree:
                requestAutorInfoThree();
                break;
            case R.id.tvRetrofitWithRxJava:
                /**Retrofit + RxJava2用法：URL是完整的链接**/
                requestRetrofitWithRxJava(baseCasrUrl);
                break;
            case R.id.tvRetrofitAndMap:
                /**单纯的Retrofit用法：URL通过map参数动态生成**/
                requestRetrofitAndMap(baseCasrUrl);
                break;
            case R.id.tvRetrofitWithRxJavaAndMap:
                /**Retrofit + RxJava2用法：URL通过map参数动态生成**/
                requestRetrofitWithRxJavaAndMap(baseCasrUrl);
                break;
            case R.id.tvCarInfoRetrofit:
                /**单纯的Retrofit用法：URL是完整的链接**/
                requestCarInfoRetrofit(baseCasrUrl);
                break;
            case R.id.tvOther:
                startActivity(new Intent(RetrofitActivity.this, InstanceActivity.class));
                break;
            default:
                break;
        }
    }

    public void requestAutorInfoOne() {
        /**
         * 完整的URL:
         * https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
         */
        Retrofit retrofit = getRetrofitObj(baseUrl);
        ClientApi.blueServiceRxJava rxJavaService = retrofit.create(ClientApi.blueServiceRxJava.class);

        Map<String, String> params = new HashMap<>();
        params.put("q", "小王子");
        params.put("tag", "");
        params.put("start", "0");
        params.put("count", "3");

        try {
            disposable = rxJavaService.getSearchBooks(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<BookSearchResponse>() {
                        @Override
                        public void accept(@NonNull BookSearchResponse bookSearchResponse) throws Exception {
                            try {
                                Log.i("TAG", "异步请求：response ===" +
                                        bookSearchResponse.toString() + "\n" + bookSearchResponse.books.size());
                                tvQuerrycontent.setText(bookSearchResponse.toString() + "\n" + "\n" + bookSearchResponse.books.size() +
                                        "\n" + "\n" + bookSearchResponse.books.get(0).alt_title);
                            } catch (Exception e) {
                                Log.i("TAG", "异步请求发生异常 try===" + e);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.i("TAG", "异步请求发生异常 Throwable===" + throwable);
                        }
                    });
        } catch (Exception e) {
            Log.i("TAG", "异步请求发生异常===" + e);
        }
    }

    public void requestAutorInfoTwo() {
        /**
         * 完整的URL:
         * https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
         */
        Retrofit retrofit = getRetrofitObj(baseUrl);
        ClientApi.BlueService service = retrofit.create(ClientApi.BlueService.class);
        Map<String, String> params = new HashMap<>();
        params.put("q", "小王子");
        params.put("tag", "");
        params.put("start", "0");
        params.put("count", "3");

        Call<BookSearchResponse> call = service.getSearchBooks("小王子", null, 0, 3);
//        Call<BookSearchResponse> call = service.getSearchBooks("小王子", "", 0, 3);
        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                BookSearchResponse mBookSearch = response.body();
                try {
                    Log.i("TAG", "onResponse: 异步请求：response ===" +
                            mBookSearch.toString() + "\n" + mBookSearch.books.size());
                    tvQuerrycontent.setText(mBookSearch.toString() + "\n" + "\n" + mBookSearch.books.size() +
                            "\n" + "\n" + mBookSearch.books.get(0).alt_title);
                } catch (Exception e) {
                    Log.i("TAG", "异步请求发生异常===" + e);
                }
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                Log.i("TAG", "异步请求失败 Throwable===" + t);
            }
        });
    }

    public void requestAutorInfoThree() {
        /**
         * 完整的URL:
         * https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
         */
        Retrofit retrofit = getRetrofitObj(baseUrl);
        ClientApi.BlueService service = retrofit.create(ClientApi.BlueService.class);

        Map<String, String> params = new HashMap<>();
        params.put("q", "小王子");
        params.put("tag", "");
        params.put("start", "0");
        params.put("count", "3");

        Call<BookSearchResponse> call = service.getSearchBooks(params);
        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                BookSearchResponse mBookSearch = response.body();
                try {
                    Log.i("TAG", "onResponse: 异步请求：response ===" +
                            mBookSearch.toString() + "\n" + mBookSearch.books.size());
                    tvQuerrycontent.setText(mBookSearch.toString() + "\n" + "\n" + mBookSearch.books.size() +
                            "\n" + "\n" + mBookSearch.books.get(0).alt_title);
                } catch (Exception e) {
                    Log.i("TAG", "异步请求发生异常===" + e);
                }
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                Log.i("TAG", "异步请求失败 Throwable===" + t);
            }
        });
    }

    /**单纯的Retrofit用法：URL是完整的链接**/
    public void requestCarInfoRetrofit(String  baseUrl){
        Retrofit rettofit = getRetrofitObj(baseUrl);
        ClientApi.GetCarInfoService retrofitCarServise = rettofit.create(ClientApi.GetCarInfoService.class);

        try{
            Call<CarBean> call = retrofitCarServise.getCall();
            call.enqueue(new Callback<CarBean>() {
                @Override
                public void onResponse(Call<CarBean> call, Response<CarBean> response) {
                    CarBean carBean = response.body();
                    try{
                        Log.i(TAG, "onResponse: 异步请求：response ===" + carBean.toString() + "\n" +
                                "carBean.translation.size()===" + carBean.translation.size() +
                                "|||||||||||" + carBean.translation .toString() + "\n" +
                                "carBean.web.size()===" + carBean.web.size() +
                                "|||||||||||" + carBean.web.toString());
                        tvQuerrycontent.setText(carBean.toString() + "\n" +
                                "carBean.translation.size()===" + carBean.translation.size() +
                                "|||||||||||" + carBean.translation .toString() + "\n" +
                                "carBean.web.size()===" + carBean.web.size() +
                                "|||||||||||" + carBean.web.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<CarBean> call, Throwable t) {
                    Log.i("TAG", "异步请求失败 Throwable===" + t);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**Retrofit + RxJava2用法：URL通过map参数动态生成**/
    public void requestRetrofitWithRxJavaAndMap(String baseUrl){
        Retrofit rettofit = getRetrofitObj(baseUrl);
        ClientApi.GetCarInfoService rxJavaCarServise = rettofit.create(ClientApi.GetCarInfoService.class);

        Map<String, String> parms = parmsCar();
        disposable = rxJavaCarServise.getRxJavaCarInfo(parms)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(@NonNull CarBean carBean) throws Exception {
                        Log.i(TAG, "onResponse: 异步请求：response ===" + carBean.toString() + "\n" +
                                "carBean.translation.size()===" + carBean.translation.size() +
                                "|||||||||||" + carBean.translation .toString() + "\n" +
                                "carBean.web.size()===" + carBean.web.size() +
                                "|||||||||||" + carBean.web.toString());
                        tvQuerrycontent.setText(carBean.toString() + "\n" +
                                "carBean.translation.size()===" + carBean.translation.size() +
                                "|||||||||||" + carBean.translation .toString() + "\n" +
                                "carBean.web.size()===" + carBean.web.size() +
                                "|||||||||||" + carBean.web.toString());
                    }
                });

    }

    /**单纯的Retrofit用法：URL通过map参数动态生成**/
    public void requestRetrofitAndMap(String baseUrl){
        Retrofit rettofit = getRetrofitObj(baseUrl);
        ClientApi.GetCarInfoService retrofitCarService = rettofit.create(ClientApi.GetCarInfoService.class);

        Map<String, String> parms = parmsCar();
        Call<CarBean> call = retrofitCarService.getCarInfo(parms);
        call.enqueue(new Callback<CarBean>() {
            @Override
            public void onResponse(Call<CarBean> call, Response<CarBean> response) {
                CarBean carBean = response.body();
                try{
                    Log.i(TAG, "onResponse: 异步请求：response ===" + carBean.toString() + "\n" +
                            "carBean.translation.size()===" + carBean.translation.size() +
                            "|||||||||||" + carBean.translation .toString() + "\n" +
                            "carBean.web.size()===" + carBean.web.size() +
                            "|||||||||||" + carBean.web.toString());
                    tvQuerrycontent.setText(carBean.toString() + "\n" +
                            "carBean.translation.size()===" + carBean.translation.size() +
                            "|||||||||||" + carBean.translation .toString() + "\n" +
                            "carBean.web.size()===" + carBean.web.size() +
                            "|||||||||||" + carBean.web.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<CarBean> call, Throwable t) {
                Log.i("TAG", "异步请求失败 Throwable===" + t);
                Log.i(TAG, "异步请求失败 onFailure: " + call.isCanceled());
            }
        });

    }

    /**Retrofit + RxJava2用法：URL是完整的链接**/
    public void requestRetrofitWithRxJava(String baseUrl){
        Retrofit retofit = getRetrofitObj(baseUrl);
        ClientApi.GetCarInfoService retrofitCarServise = retofit.create(ClientApi.GetCarInfoService.class);

        disposable = retrofitCarServise.getRxJavaCarInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(@NonNull CarBean carBean) throws Exception {
                        Log.i(TAG, "onResponse: 异步请求：response ===" + carBean.toString() + "\n" +
                                "carBean.translation.size()===" + carBean.translation.size() +
                                "|||||||||||" + carBean.translation .toString() + "\n" +
                                "carBean.web.size()===" + carBean.web.size() +
                                "|||||||||||" + carBean.web.toString());
                        tvQuerrycontent.setText(carBean.toString() + "\n" +
                                "carBean.translation.size()===" + carBean.translation.size() +
                                "|||||||||||" + carBean.translation .toString() + "\n" +
                                "carBean.web.size()===" + carBean.web.size() +
                                "|||||||||||" + carBean.web.toString());
                    }
                });
    }

    public Map<String, String> parmsCar(){
        Map<String, String> parms = new HashMap<>();
        parms.put("keyfrom", "Yanzhikai");
        parms.put("key", "2032414398");
        parms.put("type", "data");
        parms.put("doctype", "json");
        parms.put("version", "1.1");
        parms.put("q", "car");
        return parms;
    }

    public Retrofit getRetrofitObj(String baseUrl){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(BaseApplication.getOkHttpClient())
                .build();

        return retrofit;
    }

    public void cancelReq(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            Log.i(TAG, "cancelReq: 取消了网络请求===================================");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelReq(disposable);
    }
}
