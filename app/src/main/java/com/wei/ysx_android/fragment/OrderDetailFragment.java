package com.wei.ysx_android.fragment;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.MainActivity;
import com.wei.ysx_android.R;
import com.wei.ysx_android.adapter.OrderGoodsListAdapter;
import com.wei.ysx_android.decoration.MyDividerItemDecoration;
import com.wei.ysx_android.http.OrderApi;
import com.wei.ysx_android.model.Order;

public class OrderDetailFragment extends Fragment {

    TextView createTime;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    RadioGroup radioGroup;


    private Order order;

    public OrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        order = activity.order;
        initView();
        initData();
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new OrderGoodsListAdapter(order.getGoodsWithNumList());
        createTime = getActivity().findViewById(R.id.textView46);
        radioGroup = getActivity().findViewById(R.id.rg);
        RecyclerView rv = this.getActivity().findViewById(R.id.orderListDetail);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.payed:
                        OrderApi.updatePayState(order.getId(), true);
                        break;
                    case R.id.no_pay:
                        OrderApi.updatePayState(order.getId(), false);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void initData() {

        createTime.setText(order.getCreateTime());
    }

}