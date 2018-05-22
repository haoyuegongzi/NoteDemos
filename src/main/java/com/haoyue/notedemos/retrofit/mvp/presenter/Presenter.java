package com.haoyue.notedemos.retrofit.mvp.presenter;

import android.util.Log;

import com.haoyue.notedemos.retrofit.mvp.beaninfo.DouBanBean;
import com.haoyue.notedemos.retrofit.mvp.moudle.InterfaceModle;
import com.haoyue.notedemos.retrofit.mvp.moudle.MoudleImplLogic;
import com.haoyue.notedemos.retrofit.mvp.view.InterfaceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：chen1 on 2018/3/8 10
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public class Presenter {
    InterfaceModle interfaceModle;
    InterfaceView interfaceView;

    String requestStyle;

    public Presenter(InterfaceView view) {
        this.interfaceView = view;
        interfaceModle = new MoudleImplLogic();
    }

        public void getRequestData(String baseURL, String requestStyle) {
        this.requestStyle = requestStyle;
        if (baseURL.isEmpty() || requestStyle.isEmpty()) {
            interfaceView.requestFailed("baseURL或者requestStyle为空");
            return;
        }
        interfaceModle.getRequestData(baseURL, requestStyle);
    }
}
