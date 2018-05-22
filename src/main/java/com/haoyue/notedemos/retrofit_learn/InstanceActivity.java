package com.haoyue.notedemos.retrofit_learn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haoyue.notedemos.R;
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

/**
 * @author chen1
 */
public class InstanceActivity extends AppCompatActivity {

    String baseUrl = "http://fy.iciba.com/";
    String requestMoudle = "get";
    Disposable disposable;

    @BindView(R.id.tvGetRequest)
    TextView tvGetRequest;
    @BindView(R.id.tvPostRequest)
    TextView tvPostRequest;
    @BindView(R.id.tvRetrofit)
    TextView tvRetrofit;
    @BindView(R.id.tvRetrofitWithMap)
    TextView tvRetrofitWithMap;
    @BindView(R.id.tvRetrofitAndRxJavaWithMap)
    TextView tvRetrofitAndRxJavaWithMap;
    @BindView(R.id.tvRetrofitAndRxJava)
    TextView tvRetrofitAndRxJava;
    @BindView(R.id.tvInstanceContent)
    TextView tvInstanceContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instance);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvGetRequest, R.id.tvPostRequest, R.id.tvRetrofit, R.id.tvRetrofitWithMap,
              R.id.tvRetrofitAndRxJavaWithMap, R.id.tvRetrofitAndRxJava})
    public void onViewClicked(View view) {
        ClientApi.LanguageService languageService = getLanguageServiceObj(baseUrl);
        switch (view.getId()) {
            case R.id.tvGetRequest:
                requestMoudle = "get";
                break;
            case R.id.tvPostRequest:
                requestMoudle = "post";
                break;
            case R.id.tvRetrofit:
                requestRetrofit(languageService);
                break;
            case R.id.tvRetrofitWithMap:
                requestvRetrofitWithMap(languageService, "hello world");
                break;
            case R.id.tvRetrofitAndRxJavaWithMap:
                requestRetrofitAndRxJavaWithMap(languageService, "我爱华华");
                break;
            case R.id.tvRetrofitAndRxJava:
                requestRetrofitAndRxJava(languageService);
                break;
            default:
                break;
        }
    }

    public void requestRetrofit(ClientApi.LanguageService languageService){
        Call<InstanceBean> call = languageService.getRetrofit();
        call.enqueue(new Callback<InstanceBean>() {
            @Override
            public void onResponse(Call<InstanceBean> call, Response<InstanceBean> response) {
                InstanceBean instanceBean = response.body();
                Log.i("TAG", "requestRetrofit →→ onResponse: 纯Retrofit网络请求的结果 ===" + instanceBean.toString() + "\n" +
                        instanceBean.content.toString());
            }

            @Override
            public void onFailure(Call<InstanceBean> call, Throwable t) {
                Log.i("TAG", "requestRetrofit →→ onFailure: 纯Retrofit网络请求出现异常");
            }
        });
    }

    public void requestvRetrofitWithMap(ClientApi.LanguageService languageService, String translation){
        Map<String, String> map = new HashMap<>();
        map.put("a", "fy");
        map.put("f", "auto");
        map.put("t", "auto");
        map.put("w", translation);
        Call<InstanceBean> call = languageService.getRetrofitWithMap(map);
        call.enqueue(new Callback<InstanceBean>() {
            @Override
            public void onResponse(Call<InstanceBean> call, Response<InstanceBean> response) {
                InstanceBean instanceBean = response.body();
                Log.i("TAG", "requestvRetrofitWithMap →→  onResponse: 纯Retrofit网络请求的结果 ===" + instanceBean.toString() + "\n" +
                        instanceBean.content.toString());
            }

            @Override
            public void onFailure(Call<InstanceBean> call, Throwable t) {
                Log.i("TAG", "requestvRetrofitWithMap →→  onFailure: 纯Retrofit网络请求出现异常");
            }
        });

    }

    public void requestRetrofitAndRxJavaWithMap(ClientApi.LanguageService languageService, String translation){
        Map<String, String> map = new HashMap<>();
        map.put("a", "fy");
        map.put("f", "auto");
        map.put("t", "auto");
        map.put("w", translation);
        disposable = languageService.getRetrofitAndRxJavaWithMap(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<InstanceBean>() {
                    @Override
                    public void accept(@NonNull InstanceBean instanceBean) throws Exception {
                        Log.i("TAG", "requestvRetrofitWithMap →→  onResponse: 纯Retrofit网络请求的结果 ===" +
                                instanceBean.toString() + "\n" + instanceBean.content.toString());
                    }
                });
    }

    public void requestRetrofitAndRxJava(ClientApi.LanguageService languageService){
        disposable = languageService.getRetrofitAndRxJava().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<InstanceBean>() {
                    @Override
                    public void accept(@NonNull InstanceBean instanceBean) throws Exception {
                        Log.i("TAG", "requestvRetrofitWithMap →→  onResponse: 纯Retrofit网络请求的结果 ===" +
                                instanceBean.toString() + "\n" + instanceBean.content.toString());
                    }


                });
    }

    public ClientApi.LanguageService getLanguageServiceObj(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(BaseApplication.getOkHttpClient())
                .build();
        ClientApi.LanguageService languageService = retrofit.create(ClientApi.LanguageService.class);
        return languageService;
    }
}
