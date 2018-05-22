package com.haoyue.notedemos.toutiao_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.haoyue.notedemos.R;
import com.haoyue.notedemos.drawable.AdaptiveImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/1/23 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class ToutiaoAdapter extends RecyclerView.Adapter<ToutiaoAdapter.TouTiaoViewHolder> {
    private List<ImageBean> beanList;
    private Context context;
    private RequestOptions options;

    public ToutiaoAdapter(Context context, RequestOptions options) {
        this.context = context;
        this.options = options;
    }

    public void refreshData(List<ImageBean> beanList){
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public TouTiaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TouTiaoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_toutiao, parent, false));
    }

    @Override
    public void onBindViewHolder(TouTiaoViewHolder holder, int position) {
        Glide.with(context).load(beanList.get(position).imageUrl).apply(options).into(holder.adImageItem);
    }

    @Override

    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class TouTiaoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adImageItem)
        AdaptiveImageView adImageItem;
        public TouTiaoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
