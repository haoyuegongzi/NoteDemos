package com.haoyue.notedemos.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 作者：chen1 on 2018/2/9 17
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class ViewPagerStateAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public ViewPagerStateAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size() > 0 ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.size() > 0 ? titles.get(position) : "";
    }
}
