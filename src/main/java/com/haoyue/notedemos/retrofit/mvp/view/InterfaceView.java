package com.haoyue.notedemos.retrofit.mvp.view;

import com.haoyue.notedemos.retrofit.mvp.beaninfo.DouBanBean;

/**
 * 作者：chen1 on 2018/3/8 10
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public interface InterfaceView {
    void requestSuccess(int requestCode, DouBanBean douBanBean);
    void requestFailed(String msg);
}
