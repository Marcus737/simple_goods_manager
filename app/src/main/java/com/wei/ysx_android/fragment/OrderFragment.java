package com.wei.ysx_android.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.wei.ysx_android.R;
import com.wei.ysx_android.adapter.OrderListAdapter;
import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.http.OrderApi;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Goods;
import com.wei.ysx_android.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getActivity().findViewById(R.id.imageView10);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                GoodsApi.listGoods(new CommonListener() {
                    @Override
                    public <T> void callBack(final T t) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                OrderAddDetailFragment.backup = (List<Goods>) t;
                                NavController controller = Navigation.findNavController(v);
                                controller.navigate(R.id.action_orderFragment_to_orderAddDetailFragment);
                            }
                        });
                    }
                });

            }
        });
        View orderList = getActivity().findViewById(R.id.imageView11);
        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                OrderApi.listOrder(new CommonListener() {
                    @Override
                    public <T> void callBack(final T t) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                OrderListAdapter.orders = (List<Order>) t;
                                NavController controller = Navigation.findNavController(v);
                                controller.navigate(R.id.action_orderFragment_to_orderListFragment);
                            }
                        });
                    }
                });
            }
        });
        View orderNoPayList = getActivity().findViewById(R.id.imageView12);
        orderNoPayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (OrderListAdapter.orders == null){
                    OrderApi.listOrder(new CommonListener() {
                        @Override
                        public <T> void callBack(final T t) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    OrderListAdapter.orders = (List<Order>) t;
                                    OrderListAdapter.orders = getNoPayOrders(OrderListAdapter.orders);
                                    NavController controller = Navigation.findNavController(v);
                                    controller.navigate(R.id.action_orderFragment_to_orderNoPayFragment);
                                }
                            });
                        }
                    });
                }else {
                    OrderListAdapter.orders = getNoPayOrders(OrderListAdapter.orders);
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_orderFragment_to_orderNoPayFragment);
                }
            }
        });
    }

    private List<Order> getNoPayOrders(List<Order> orders) {
        ArrayList<Order> to = new ArrayList<>();
        for (Order order : orders) {
            if (order.getState().equals("0")){
                to.add(order);
            }
        }
        return to;
    }
}