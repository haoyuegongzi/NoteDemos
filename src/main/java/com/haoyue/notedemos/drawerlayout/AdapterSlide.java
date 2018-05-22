package com.haoyue.notedemos.drawerlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoyue.notedemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/1/25 13
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class AdapterSlide extends RecyclerView.Adapter<AdapterSlide.ViewHolder> implements View.OnClickListener{

    Context context;
    String[]itemData;
    OnItemClickListener onItemClickListener;

    public AdapterSlide(Context context){
        this.context = context;
    }

    public void refreshItemData(String[]itemData){
        this.itemData = itemData;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_slide, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvDrawerItem.setText(itemData[position]);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return itemData==null ? 0 : itemData.length;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener == null) {
            onItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    public String getItemContent(int position){
        return itemData[position];
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        onItemClickListener = mOnItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDrawerItem)
        TextView tvDrawerItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
