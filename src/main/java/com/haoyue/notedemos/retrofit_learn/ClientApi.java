package com.haoyue.notedemos.retrofit_learn;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * 作者：chen1 on 2018/1/16 15
 * E——Mail：
 * TODO：
 */

public class ClientApi {

    public interface GetRequest_Interface{
        @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
        Call<Translation> getCall();
    }

    public interface BlueService {
        /**
         * 完整的URL:https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
         */
        /**https://api.douban.com/v2/    book/search    ?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
            这里book/search的的由来：上面完整的URL中，除去baseUrl后开头的字段，如上v2之后？之前的部分。**/
        @GET("book/search")
        Call<BookSearchResponse> getSearchBooks(@Query("q") String name,
                                                @Query("tag") String tag,
                                                @Query("start") int start,
                                                @Query("count") int count);

        @GET("book/search")
        Call<BookSearchResponse> getSearchBooks(@QueryMap Map<String, String > params);
        //getSearchBooks()方法的重载
    }

    public interface blueServiceRxJava{
        @GET("book/search")
        Observable<BookSearchResponse> getSearchBooks(@QueryMap Map<String, String> map);
    }

    public interface  GetCarInfoService{
        /**单纯的Retrofit用法：URL是完整的链接**/
        @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
        Call<CarBean> getCall();

        /**单纯的Retrofit用法：URL通过map参数动态生成**/
        @GET("openapi.do")
        Call<CarBean> getCarInfo(@QueryMap Map<String, String> parms);

        /**Retrofit + RxJava2用法：URL通过map参数动态生成**/
        @GET("openapi.do")
        Observable<CarBean> getRxJavaCarInfo(@QueryMap Map<String, String> parms);

        /**Retrofit + RxJava2用法：URL是完整的链接**/
        @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
        Observable<CarBean> getRxJavaCarInfo();
    }

    /**http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world**/
    public interface LanguageService{
        @GET("ajax.php")
        Call<InstanceBean> getRetrofitWithMap(@QueryMap Map<String, String> parms);

        @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
        Call<InstanceBean> getRetrofit();

        @GET("ajax.php")
        Observable<InstanceBean> getRetrofitAndRxJavaWithMap(@QueryMap Map<String, String> parms);

        @GET("ajax.php?a=fy&f=auto&t=auto&w=我爱华华")
        Observable<InstanceBean> getRetrofitAndRxJava();
    }

    public interface streamReq{
        @Streaming
        @GET
        Call<BookSearchResponse> call(@QueryMap Map<String, String > params);

        @Streaming
        @POST
        Call<BookSearchResponse> callPost(@QueryMap Map<String, String > params);
    }
}
