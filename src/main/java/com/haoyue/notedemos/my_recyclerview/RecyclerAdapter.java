package com.haoyue.notedemos.my_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * 作者：chen1 on 2018/2/5 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class RecyclerAdapter extends RecyclerItemClickAdapter{


    public RecyclerAdapter(Context context, ArrayList datas, int layoutID) {
        super(context, datas, layoutID);
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View itemView) {
        return null;
    }

    @Override
    protected void setValues(RecyclerView.ViewHolder holder, Object bean) {

    }
}
