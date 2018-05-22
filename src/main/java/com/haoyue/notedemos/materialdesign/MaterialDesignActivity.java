package com.haoyue.notedemos.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.drawerlayout.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chen1
 */
public class MaterialDesignActivity extends AppCompatActivity {

    ActionBarDrawerToggle drawerToggle;
    ListAdapter listAdapter;
    MaterialDesignPagerAdapter designPagerAdapter;
    MaterialDesignStatePagerAdapter statePagerAdapter;

    MaterialDesignFragment designFragment01, designFragment02, designFragment03, designFragment04;
    List<MaterialDesignFragment> fragmentList;
    List<String> stringList;

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.dlDrawerLayout)
    DrawerLayout dlDrawerLayout;
    @BindView(R.id.rvDrawerLayout)
    ListView rvDrawerLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE));
        // 和getFragmentManager()和getSupprotFragmentManager()差不多
        // 隐藏掉系统原先的导航栏,用Toolbar代替
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_material_design);
        ButterKnife.bind(this);
        initToolBar();
        initDrawerLayout();
        addDataToRecyclerView();
        initFragment();
    }

    private void initFragment() {
        designFragment01 = new MaterialDesignFragment();
        designFragment02 = new MaterialDesignFragment();
        designFragment03 = new MaterialDesignFragment();
        designFragment04 = new MaterialDesignFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(designFragment01);
        fragmentList.add(designFragment02);
        fragmentList.add(designFragment03);
        fragmentList.add(designFragment04);
        stringList = new ArrayList<>();
        stringList.add("秋水浮萍 \n任缥缈");
        stringList.add("孤鸿寄语 \n默苍离");
        stringList.add("黑白郎君 \n南宫恨");
        stringList.add("天刑道者 \n岳灵休");
        pagerAdapter();
    }


    private void pagerAdapter() {
        designPagerAdapter = new MaterialDesignPagerAdapter(getSupportFragmentManager());
        designPagerAdapter.refreshData(stringList, fragmentList);

        statePagerAdapter = new MaterialDesignStatePagerAdapter(getSupportFragmentManager());
        statePagerAdapter.refreshData(stringList, fragmentList);

        tabLayout.setupWithViewPager(viewPager, false);

        viewPager.setAdapter(statePagerAdapter);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        findViewById(R.id.tvSophix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MaterialDesignActivity.this, SophixActivity.class));
            }
        });
    }

    private void initToolBar() {
        //设置logo
//        toolBar.setLogo(R.mipmap.ic_launcher);
        //设置导航的图标：类似点击它就出现侧滑菜单的那个
        toolBar.setNavigationIcon(R.drawable.menu);

        //设置标题及颜色
//        toolBar.setTitle(getResources().getString(R.string.toolbar_title));
//        toolBar.setTitleTextColor(getResources().getColor(R.color.toolbar_title));
//        //设置子标题(子标题可以没有)及颜色
//        toolBar.setSubtitle(getResources().getString(R.string.subtitle));
//        toolBar.setSubtitleTextColor(getResources().getColor(R.color.toolbar_title));

        setSupportActionBar(toolBar);
    }

    private void initDrawerLayout() {
        drawerToggle = new ActionBarDrawerToggle(MaterialDesignActivity.this,
                dlDrawerLayout, toolBar, R.string.open, R.string.close);
        drawerToggle.syncState();//将ActionDrawerToggle与DrawerLayout的状态同步
        dlDrawerLayout.addDrawerListener(drawerToggle);
    }

    private void addDataToRecyclerView() {
        final List<String> stringList = initRecyclerViewData();
        listAdapter = new ListAdapter(getApplicationContext());
        rvDrawerLayout.setAdapter(listAdapter);
        listAdapter.refreshData(stringList);
        rvDrawerLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dlDrawerLayout.closeDrawers();
                Toast.makeText(MaterialDesignActivity.this, stringList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.i("TAG", "onOptionsItemSelected: action_search");
                break;
            case R.id.action_add:
                Log.i("TAG", "onOptionsItemSelected: action_add");
                break;
            case R.id.action_setting:
                Log.i("TAG", "onOptionsItemSelected: action_setting");
                break;
            case R.id.action_help:
                Log.i("TAG", "onOptionsItemSelected: action_help");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<String> initRecyclerViewData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("侧滑菜单栏区域00");
        stringList.add("侧滑菜单栏区域01");
        stringList.add("侧滑菜单栏区域02");
        stringList.add("侧滑菜单栏区域03");
        stringList.add("侧滑菜单栏区域04");
        stringList.add("侧滑菜单栏区域05");
        stringList.add("侧滑菜单栏区域06");
        stringList.add("侧滑菜单栏区域07");
        stringList.add("侧滑菜单栏区域08");
        stringList.add("侧滑菜单栏区域09");
        stringList.add("侧滑菜单栏区域10");
        stringList.add("侧滑菜单栏区域11");
        stringList.add("侧滑菜单栏区域12");
        stringList.add("侧滑菜单栏区域13");
        stringList.add("侧滑菜单栏区域14");
        return stringList;
    }
}
