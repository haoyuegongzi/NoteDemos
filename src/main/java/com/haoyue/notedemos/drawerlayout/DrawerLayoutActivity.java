package com.haoyue.notedemos.drawerlayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.notedemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerLayoutActivity extends AppCompatActivity {

    ActionBarDrawerToggle mDrawerToggle;
    String[] slide = new String[]{"侧滑1", "侧滑2", "侧滑3", "侧滑4", "侧滑5", "侧滑6", "侧滑7", "侧滑8", "侧滑9", "侧滑10"};
    ListAdapter listAdapter;

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.lvLeftDrawer)
    ListView lvLeftDrawer;
    @BindView(R.id.dlSideSlide)
    DrawerLayout dlSideSlide;
//    @BindView(R.id.tvOpenSlide)
//    TextView mTvOpenSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);
        listAdapter = new ListAdapter(getApplicationContext());
        addSlideMenuListener();
        setSlideMenuData();
    }

    private void addSlideMenuListener() {
        mDrawerToggle = new ActionBarDrawerToggle(this, dlSideSlide, R.string.open_drawer, R.string.close_drawer);
        dlSideSlide.addDrawerListener(mDrawerToggle);
    }

    private void setSlideMenuData() {
        lvLeftDrawer.setAdapter(listAdapter);
        listAdapter.refreshData(slide);
        lvLeftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemcontent = adapterView.getAdapter().getItem(i).toString();
                Toast.makeText(DrawerLayoutActivity.this, itemcontent, Toast.LENGTH_LONG).show();
                dlSideSlide.closeDrawers();
            }
        });
    }
}
