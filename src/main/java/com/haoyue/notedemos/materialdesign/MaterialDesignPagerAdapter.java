package com.haoyue.notedemos.materialdesign;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：chen1 on 2018/3/15 10
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class MaterialDesignPagerAdapter extends FragmentPagerAdapter {
    List<MaterialDesignFragment> fragmentList;
    List<String> stringList;

    public MaterialDesignPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public void refreshData(List<String> stringList, List<MaterialDesignFragment> fragmentList){
        this.fragmentList = fragmentList;
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.size() > 0 ? stringList.get(position) : "";
    }
}
