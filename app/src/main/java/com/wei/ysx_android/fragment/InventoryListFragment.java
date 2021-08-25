package com.wei.ysx_android.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.adapter.GoodsListAdapter;
import com.wei.ysx_android.adapter.InventoryListAdapter;
import com.wei.ysx_android.decoration.MyDividerItemDecoration;
import com.wei.ysx_android.model.Goods;
import com.wei.ysx_android.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryListFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public InventoryListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        RecyclerView rv = this.getActivity().findViewById(R.id.inventoryListRecycleView);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new InventoryListAdapter();
    }

}