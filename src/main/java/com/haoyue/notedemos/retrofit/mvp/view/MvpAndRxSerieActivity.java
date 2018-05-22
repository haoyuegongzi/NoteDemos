package com.haoyue.notedemos.retrofit.mvp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.retrofit.mvp.beaninfo.DouBanBean;
import com.haoyue.notedemos.retrofit.mvp.beaninfo.EventBusMsg;
import com.haoyue.notedemos.retrofit.mvp.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class MvpAndRxSerieActivity  extends BaseActivity implements InterfaceView {

    Presenter presenter;
    String requestStyle = "GetRetrofit";
    String baseURL = "https://api.douban.com/v2/";

    @BindView(R.id.tvGetRetrofit)
    TextView tvGetRetrofit;
    @BindView(R.id.tvGetRetrofitWithMap)
    TextView tvGetRetrofitWithMap;
    @BindView(R.id.tvGetRetrofitAndRxJavaWithMap)
    TextView tvGetRetrofitAndRxJavaWithMap;
    @BindView(R.id.tvGetRetrofitAndRxJava)
    TextView tvGetRetrofitAndRxJava;
    @BindView(R.id.tvPostRetrofit)
    TextView tvPostRetrofit;
    @BindView(R.id.tvPostRetrofitWithMap)
    TextView tvPostRetrofitWithMap;
    @BindView(R.id.tvPostRetrofitAndRxJavaWithMap)
    TextView tvPostRetrofitAndRxJavaWithMap;
    @BindView(R.id.tvPostRetrofitAndRxJava)
    TextView tvPostRetrofitAndRxJava;
    @BindView(R.id.tvRequestContent)
    TextView tvRequestContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_and_rx_serie);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        presenter = new Presenter(this);
    }

    @Override
    public void requestSuccess(int requestCode, DouBanBean douban) {
        switch (requestCode){
            case 0:
                tvRequestContent.setText("carInfo →→→" + douban.toString() + "\n" +
                                        "carInfo.translation.size() ===" + douban.books.toString() + "\n" +
                                        "requestStyle ===" + requestStyle);
                break;
            case 1:
                requestFailed("数据异常，请稍后再试");
                break;
            case 2:
                requestFailed("网络请求失败，请稍后再试");
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusMsg msg){
        switch (msg.flag){
            case "MvpAndRxSerieActivity":
                requestSuccess(msg.requestCode, msg.douBanBean);
                break;
            default:
                break;
        }
    }

    @Override
    public void requestFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }

    @OnClick({R.id.tvGetRetrofit, R.id.tvGetRetrofitWithMap, R.id.tvGetRetrofitAndRxJavaWithMap,
              R.id.tvGetRetrofitAndRxJava, R.id.tvPostRetrofit, R.id.tvPostRetrofitWithMap,
              R.id.tvPostRetrofitAndRxJavaWithMap, R.id.tvPostRetrofitAndRxJava})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetRetrofit:
                requestStyle = "GetRetrofit";
                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
                presenter.getRequestData(baseURL, requestStyle);
                break;
            case R.id.tvGetRetrofitWithMap:
//                requestStyle = "GetRetrofitWithMap";
//                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
//                presenter.getRequestData(baseURL, requestStyle);
                break;
            case R.id.tvGetRetrofitAndRxJavaWithMap:
//                requestStyle = "GetRetrofitAndRxJavaWithMap";
//                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
//                presenter.getRequestData(baseURL, requestStyle);
                break;
            case R.id.tvGetRetrofitAndRxJava:
//                requestStyle = "GetRetrofitAndRxJava";
//                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
//                presenter.getRequestData(baseURL, requestStyle);
                break;
            case R.id.tvPostRetrofit:
                requestStyle = "PostRetrofit";
                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
                break;
            case R.id.tvPostRetrofitWithMap:
                requestStyle = "PostRetrofitWithMap";
                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
                break;
            case R.id.tvPostRetrofitAndRxJavaWithMap:
                requestStyle = "PostRetrofitAndRxJavaWithMap";
                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
                break;
            case R.id.tvPostRetrofitAndRxJava:
                requestStyle = "PostRetrofitAndRxJava";
                Log.i(TAG, "onViewClicked: requestStyle ===" + requestStyle);
                break;
            default:
                break;
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
        EventBus.getDefault().unregister(this);
    }
}