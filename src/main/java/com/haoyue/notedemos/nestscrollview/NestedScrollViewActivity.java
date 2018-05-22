package com.haoyue.notedemos.nestscrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.haoyue.auxiliary.AdaptiveImageView;
import com.haoyue.notedemos.R;
import com.haoyue.notedemos.tablayout.TabLayoutActivity;
import com.haoyue.notedemos.tablayout.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/2/7 13
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */
public class NestedScrollViewActivity extends AppCompatActivity {

    @BindView(R.id.customTitle)
    TextView customTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rvNestedScrollView)
    RecyclerView rvNestedScrollView;

    List<String> integerList;
    NestedScrollViewAdapter adapter;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
//    @BindView(R.id.imageView)
//    AdaptiveImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);
        ButterKnife.bind(this);

        toolBar.setTitle("");
        customTitle.setText("NestedScrollView的练习");
        setSupportActionBar(toolBar);

        integerList = intiData();
        intiData();

        mainViewListData();
    }

    private List<String> intiData() {
        return InitData.getInstance().integerList();
    }

    private void mainViewListData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNestedScrollView.setLayoutManager(manager);
        adapter = new NestedScrollViewAdapter(this, integerList);
        rvNestedScrollView.setAdapter(adapter);
        rvNestedScrollView.setNestedScrollingEnabled(false);
        customTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NestedScrollViewActivity.this, TabLayoutActivity.class));
            }
        });
    }
}
