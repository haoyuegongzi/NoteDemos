package com.haoyue.notedemos.my_recyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoyue.notedemos.R;

/**
 * Created by BONC on 2018/2/2.
 */

public class RecyclerViewItemClickAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    public Activity activity;
    int layOutItem;
    private OnItemClickListener onItemClickListener = null;

    public RecyclerViewItemClickAdapter(Activity mActivity, int layOutItem){
        activity = mActivity;
        this.layOutItem = layOutItem;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layOutItem, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //这里进行数据的绑定

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取position
            onItemClickListener.OnItemClick(v,(int)v.getTag());
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}

