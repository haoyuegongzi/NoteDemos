package com.haoyue.notedemos.materialdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.notedemos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/3/14 17
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * stringList.size() > 0 ? stringList.size() : 0;
 * @author chen1
 */

public class DrawerLayoutAdapter extends RecyclerView.Adapter<DrawerLayoutAdapter.ViewHolder> {
    Context context;
    List<String> stringList;
//    DrawerLayout dlDrawerLayout;
    DrawerLayoutInterface drawerLayout;

    public DrawerLayoutAdapter(Context context, List<String> stringList, DrawerLayout dlDrawerLayout, DrawerLayoutInterface drawerLayout) {
        this.context = context;
        this.stringList = stringList;
//        this.dlDrawerLayout = dlDrawerLayout;
        this.drawerLayout = drawerLayout;
        Log.i("TAG", "DrawerLayoutAdapter: this.stringList.size() ===" + this.stringList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_slide, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvDrawerItem.setText(stringList.get(position));
        holder.tvDrawerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers(stringList.get(position));
                Toast.makeText(context, stringList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDrawerItem)
        TextView tvDrawerItem;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
