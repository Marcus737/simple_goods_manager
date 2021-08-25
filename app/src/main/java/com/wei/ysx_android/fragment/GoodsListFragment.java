package com.wei.ysx_android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.adapter.GoodsListAdapter;
import com.wei.ysx_android.decoration.MyDividerItemDecoration;


public class GoodsListFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public GoodsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
    }

    private void initView() {
        RecyclerView rv = this.getActivity().findViewById(R.id.goodsListRecycleView);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new GoodsListAdapter();
    }

}