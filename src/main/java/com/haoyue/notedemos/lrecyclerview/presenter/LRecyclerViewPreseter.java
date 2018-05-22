package com.haoyue.notedemos.lrecyclerview.presenter;

import com.haoyue.notedemos.lrecyclerview.moudle.LRecyclerViewMoudleInterface;
import com.haoyue.notedemos.lrecyclerview.moudle.LRecyclerViewMoudleLogic;
import com.haoyue.notedemos.lrecyclerview.util.InterfaceCallBack;
import com.haoyue.notedemos.lrecyclerview.util.WanAndroidBean;
import com.haoyue.notedemos.lrecyclerview.view.LRecyclerViewInterface;

/**
 * 作者：chen1 on 2018/3/19 10
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class LRecyclerViewPreseter {

    LRecyclerViewInterface viewInterface;
    LRecyclerViewMoudleInterface moudleInterface;

    public LRecyclerViewPreseter(LRecyclerViewInterface viewInterface){
        this.viewInterface = viewInterface;
        moudleInterface = new LRecyclerViewMoudleLogic();
    }

    public void wanAndroidList(String baseUrl, String id){
        moudleInterface.getWanAndroidList(baseUrl, id, interfaceCallBack);
    }

    InterfaceCallBack interfaceCallBack = new InterfaceCallBack() {
        @Override
        public void callBackWanAndroidList(WanAndroidBean wanAndroidBean, int requestCode) {
            switch (requestCode){
                //数据请求成功
                case 0:
                    viewInterface.requestDataSuccess(wanAndroidBean);
                    break;
                case 1:
                    viewInterface.requestDataFailed("数据请求失败，请稍后再试");
                    break;
                default:
                    break;
            }
        }
    };
}
