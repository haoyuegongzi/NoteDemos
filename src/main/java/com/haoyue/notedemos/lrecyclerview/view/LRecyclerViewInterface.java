package com.haoyue.notedemos.lrecyclerview.view;

import com.haoyue.notedemos.lrecyclerview.util.WanAndroidBean;

/**
 * 作者：chen1 on 2018/3/19 10
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public interface LRecyclerViewInterface {

    void requestDataSuccess(WanAndroidBean wanAndroidBean);
    void requestDataFailed(String failedMsg);
}
