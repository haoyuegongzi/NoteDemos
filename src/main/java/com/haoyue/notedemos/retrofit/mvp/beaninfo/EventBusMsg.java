package com.haoyue.notedemos.retrofit.mvp.beaninfo;

/**
 * 作者：chen1 on 2018/3/13 10
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class EventBusMsg {
    public String flag;
    public int requestCode;
    public DouBanBean douBanBean;

    public EventBusMsg(String flag, int requestCode, DouBanBean douBanBean) {
        this.flag = flag;
        this.requestCode = requestCode;
        this.douBanBean = douBanBean;
    }
}
