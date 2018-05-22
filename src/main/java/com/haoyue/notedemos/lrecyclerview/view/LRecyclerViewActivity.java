package com.haoyue.notedemos.lrecyclerview.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.lrecyclerview.presenter.LRecyclerViewPreseter;
import com.haoyue.notedemos.lrecyclerview.util.WanAndroidBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chen1
 */
public class LRecyclerViewActivity extends AppCompatActivity implements LRecyclerViewInterface {

    LRecyclerViewPreseter preseter;
    //http://www.wanandroid.com/article/list/0/json
    String BaseUrl = "http://www.wanandroid.com/";
    int id = 0;
    List<WanAndroidBean.DataBean.DatasBean> dataBeanList;
    WanandroidListAdapter wanandroidListAdapter;


    @BindView(R.id.rvWanAndroid)
    RecyclerView rvWanAndroid;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrecycler_view);
        ButterKnife.bind(this);
        getData();
        setRefreshLayout();
    }

    private void getData() {
        preseter = new LRecyclerViewPreseter(this);
        preseter.wanAndroidList(BaseUrl, String.valueOf(id));
        wanandroidListAdapter = new WanandroidListAdapter(LRecyclerViewActivity.this);
    }

    private void setRefreshLayout(){
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefresh.finishRefresh(1500);
                preseter.wanAndroidList(BaseUrl, "0");
            }
        });

        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smartRefresh.finishLoadMore(1500);
                id++;
                preseter.wanAndroidList(BaseUrl, String.valueOf(id));
            }
        });
    }

    @Override
    public void requestDataSuccess(WanAndroidBean wanAndroidBean) {
        if (Integer.parseInt(wanAndroidBean.errorCode) == 0) {
            //TODO:update RecyclerView
            dataBeanList = wanAndroidBean.data.datas;
            setAdapterData(dataBeanList);
        } else {
            requestDataFailed(wanAndroidBean.errorMsg);
        }
    }

    @Override
    public void requestDataFailed(String failedMsg) {
        Toast.makeText(getApplication(), failedMsg, Toast.LENGTH_LONG).show();
        Log.i("TAG", "requestDataFailed: " + failedMsg);
    }

    private void setAdapterData(List<WanAndroidBean.DataBean.DatasBean> dataBeanList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWanAndroid.setLayoutManager(layoutManager);
        rvWanAndroid.setAdapter(wanandroidListAdapter);
        wanandroidListAdapter.refreshData(dataBeanList);
    }
}
