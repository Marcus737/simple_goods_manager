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
import com.wei.ysx_android.adapter.GoodsListAdapter;
import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Goods;

import java.util.List;


public class GoodsFragment extends Fragment {


    public GoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View goodsAdd = this.getActivity().findViewById(R.id.imageView5);
        final NavController controller = Navigation.findNavController(goodsAdd);
        goodsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_goodsFragment_to_goodsAddDetailFragment);
            }
        });
        View goodsList = this.getActivity().findViewById(R.id.imageView6);
        goodsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsApi.listGoods(new CommonListener() {
                    @Override
                    public <T> void callBack(final T t) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GoodsListAdapter.goodsInfo = (List<Goods>) t;
                                controller.navigate(R.id.action_goodsFragment_to_goodsListFragment);
                            }
                        });
                    }
                });

            }
        });
        View updateGoods = this.getActivity().findViewById(R.id.imageView7);
        updateGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsApi.listGoods(new CommonListener() {
                    @Override
                    public <T> void callBack(final T t) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GoodsListAdapter.goodsInfo = (List<Goods>) t;
                                controller.navigate(R.id.action_goodsFragment_to_goodsListFragment);
                            }
                        });
                    }
                });
            }
        });
    }
}