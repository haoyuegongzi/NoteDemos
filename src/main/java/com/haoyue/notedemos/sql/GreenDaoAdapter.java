package com.haoyue.notedemos.sql;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoyue.notedemos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen1 on 2017/11/29.
 * TO DO:
 */

public class GreenDaoAdapter extends RecyclerView.Adapter<GreenDaoAdapter.ViewHolder> {
    private Activity mActivity;
    private List<ShopBean> shopList;

    public GreenDaoAdapter(Activity activity, List<ShopBean> list) {
        mActivity = activity;
        shopList = list;
    }

    public void refreshData(List<ShopBean> list){
        shopList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.adapter_testlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imageUrl = shopList.get(position).getImageUrl();
        Glide.with(mActivity).load(imageUrl).into(holder.mIvIcon);

//        holder.mIvIcon.setBackgroundResource(R.drawable.sunlu13);
        holder.mTvItemName.setText(shopList.get(position).getName());
        holder.mTvDiscount.setText(shopList.get(position).getPrice());
        holder.mTvHasSell.setText("已售 " + shopList.get(position).getSellNumber() + "件");
        holder.mTvOriginalPrice.setText("￥128");
        //添加删除线
        holder.mTvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mTvSellPosition.setText(shopList.get(position).getMerchantAddress());
        if (shopList.get(position).getType() == 0x001){
            holder.mTvPackageMail.setText("包邮");
        }else if(shopList.get(position).getType() == 0x002){
            holder.mTvPackageMail.setText("满199包邮");
        }
    }

    @Override
    public int getItemCount() {
        return shopList == null ? 0 : shopList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ivIcon)
        ImageView mIvIcon;
        @BindView(R.id.tvItemName)
        TextView mTvItemName;
        @BindView(R.id.tvDiscount)
        TextView mTvDiscount;
        @BindView(R.id.tvOriginalPrice)
        TextView mTvOriginalPrice;
        @BindView(R.id.tvPackageMail)
        TextView mTvPackageMail;
        @BindView(R.id.tvHasSell)
        TextView mTvHasSell;
        @BindView(R.id.tvSellPosition)
        TextView mTvSellPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
