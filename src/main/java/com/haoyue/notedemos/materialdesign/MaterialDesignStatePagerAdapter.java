package com.haoyue.notedemos.materialdesign;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 作者：chen1 on 2018/3/15 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class MaterialDesignStatePagerAdapter extends FragmentStatePagerAdapter {
    List<MaterialDesignFragment> fragmentList;
    List<String> stringList;

    public MaterialDesignStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void refreshData(List<String> stringList, List<MaterialDesignFragment> fragmentList){
        this.fragmentList = fragmentList;
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.size() > 0 ? fragmentList.get(position) : null;
    }

    @Override
    public int getCount() {
        return fragmentList.size() > 0 ? fragmentList.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.size() > 0 ? stringList.get(position) : "";
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
