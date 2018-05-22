package com.haoyue.notedemos.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haoyue.notedemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen1
 */
public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        List<Fragment> fragments = new ArrayList<>();
        Fragment aFragment = new TabLayoutFragment();
        Fragment bFragment = new TabLayoutFragment();
        Fragment cFragment = new TabLayoutFragment();
        Fragment dFragment = new TabLayoutFragment();
        Fragment eFragment = new TabLayoutFragment();
        fragments.add(aFragment);
        fragments.add(bFragment);
        fragments.add(cFragment);
        fragments.add(dFragment);
        fragments.add(eFragment);

        List<String> titles = new ArrayList<>();
        titles.add("A");
        titles.add("B");
        titles.add("C");
        titles.add("D");
        titles.add("E");

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
//        ViewPagerStateAdapter mAdapter = new ViewPagerStateAdapter(getSupportFragmentManager(), fragments, titles);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
