package com.haoyue.notedemos.drawerlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haoyue.notedemos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/1/25 15
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class ListAdapter extends BaseAdapter {

    Context context;
    String[] itemData;
    List<String> stringList;

    public ListAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(String[] itemData) {
        this.itemData = itemData;
        notifyDataSetChanged();
    }

    public void refreshData(List<String> stringList) {
        this.stringList = stringList;
        if (stringList != null && stringList.size() > 0) {
            itemData = new String[stringList.size()];
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (stringList != null && stringList.size() > 0) {
            for (int i = 0; i < stringList.size(); i++) {
                itemData[i] = stringList.get(i);
            }
        }
        return itemData == null ? 0 : itemData.length;
    }

    @Override
    public Object getItem(int i) {
        return itemData[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_slide, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvDrawerItem.setText(itemData[i]);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tvDrawerItem)
        TextView tvDrawerItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
