package com.haoyue.notedemos.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoyue.notedemos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：chen1 on 2018/2/7 18
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class TabLayoutFragment extends Fragment {


    View view;
//    @BindView(R.id.rvFragmentNested)
//    RecyclerView rvFragmentNested;
//
//    Unbinder unbinder;
//
//    TabLayoutFragmentAdapter fragmentAdapter;
//    List<String> integerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nestedscrollview, container, false);
//        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvFragmentNested.setLayoutManager(manager);
//        fragmentAdapter = new TabLayoutFragmentAdapter(getActivity(), integerList);
//        rvFragmentNested.setAdapter(fragmentAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

//    public void refreshData(List<String> integerList){
//        this.integerList = integerList;
//    }

}
