package com.wei.ysx_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wei.ysx_android.R;
import com.wei.ysx_android.databinding.FragmentGoodsDetailBinding;
import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.model.Goods;

public class GoodsDetailFragment extends Fragment {

    FragmentGoodsDetailBinding binding;

    public GoodsDetailFragment() {
        // Required empty public constructor
    }

    public static Goods goods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText viewById = getActivity().findViewById(R.id.editTextTextPersonName);
        viewById.setText(goods.getGoodsName());
        final EditText viewById2 = getActivity().findViewById(R.id.editTextTextPersonName2);
        viewById2.setText(goods.getPrice());
        final EditText viewById3 = getActivity().findViewById(R.id.editTextTextPersonName3);
        viewById3.setText(goods.getNote());
        final EditText viewById4 = getActivity().findViewById(R.id.editTextTextPersonName4);
        if (goods.getEnable()){
            viewById4.setText("启用");
        }else {
            viewById4.setText("未启用");
        }
        final EditText viewById5 = getActivity().findViewById(R.id.editTextTextPersonName5);
        viewById5.setText(goods.getManuName());
        final EditText viewById6 = getActivity().findViewById(R.id.editTextTextPersonName7);
        viewById6.setText(goods.getTrademark());
        final EditText viewById7 = getActivity().findViewById(R.id.editTextTextPersonName6);
        viewById7.setText(goods.getSpec());
        final EditText viewById8 = getActivity().findViewById(R.id.editTextTextPersonName8);
        viewById8.setText(goods.getGoodsType());
        final EditText viewById9 = getActivity().findViewById(R.id.editTextTextPersonName9);
        viewById9.setText(goods.getManuAddress());
        Button b = getActivity().findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods.setGoodsName(viewById.getText().toString());
                goods.setPrice(viewById2.getText().toString());
                goods.setNote(viewById3.getText().toString());
                goods.setEnable(true);
                goods.setManuName(viewById5.getText().toString());
                goods.setTrademark(viewById6.getText().toString());
                goods.setSpec(viewById7.getText().toString());
                goods.setGoodsType(viewById8.getText().toString());
                goods.setManuAddress(viewById9.getText().toString());
                GoodsApi.updateGoodsByCode(goods);
            }
        });
    }

}