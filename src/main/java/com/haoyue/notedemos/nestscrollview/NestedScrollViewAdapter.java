package com.haoyue.notedemos.nestscrollview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.auxiliaryutil.ToastUtils;
import com.haoyue.notedemos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/2/7 14
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class NestedScrollViewAdapter extends RecyclerView.Adapter<NestedScrollViewAdapter.ViewHolder> {

    Context context;
    List<String> integerList;

    public NestedScrollViewAdapter(Context context, List<String> integerList) {
        this.context = context;
        this.integerList = integerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_nestedscrollview, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemNestedScrollView.setText(integerList.get(position));
        holder.itemNestedScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick: 在RecyclerView中点击了第" + position + "个item");
                Toast.makeText(context, "onClick: 在RecyclerView中点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (integerList != null && integerList.size() > 0) {
            return integerList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemNestedScrollView)
        TextView itemNestedScrollView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
