package com.haoyue.notedemos.lrecyclerview.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.haoyue.auxiliary.CircleImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.lrecyclerview.util.WanAndroidBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/3/19 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class WanandroidListAdapter extends RecyclerView.Adapter<WanandroidListAdapter.ViewHolder> {

    Activity activity;
    List<WanAndroidBean.DataBean.DatasBean> dataBeanList;
    int indexPosition = 0;

    public WanandroidListAdapter(Activity activity) {
        this.activity = activity;
    }

    public void refreshData(List<WanAndroidBean.DataBean.DatasBean>dataBeanList){
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.adapter_wanandroid_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        indexPosition = position;
        holder.tvRelease.setText(dataBeanList.get(position).niceDate);
        holder.tvAuthorName.setText(dataBeanList.get(position).author);
        holder.tvArticleType.setText(dataBeanList.get(position).superChapterName);
        holder.tvArticleContent.setText(dataBeanList.get(position).title);
        if (dataBeanList.get(position).collect) {
            holder.ivFocus.setImageResource(R.mipmap.focus_add);
        }else {
            holder.ivFocus.setImageResource(R.mipmap.focus_cancel);
        }
        holder.civAuthorPhoto.setImageDrawable(activity.getDrawable(R.mipmap.ear));

        holder.llWanAndroidItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://www.jianshu.com/p/5a272afb4c2e
                Intent intent = new Intent(activity, LoadArticleActivity.class);
                intent.putExtra("link", dataBeanList.get(indexPosition).link);
                Log.i("TAG", "onClick: link ===" + dataBeanList.get(indexPosition).link);
                activity.startActivity(intent);
            }
        });
        holder.tvArticleType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.ivFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size() > 0 ? dataBeanList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civAuthorPhoto)
        CircleImageView civAuthorPhoto;
        @BindView(R.id.tvAuthorName)
        TextView tvAuthorName;
        @BindView(R.id.tvRelease)
        TextView tvRelease;
        @BindView(R.id.tvArticleContent)
        TextView tvArticleContent;
        @BindView(R.id.tvArticleType)
        TextView tvArticleType;
        @BindView(R.id.ivFocus)
        ImageView ivFocus;
        @BindView(R.id.llWanAndroidItem)
        LinearLayout llWanAndroidItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
