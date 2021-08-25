package com.wei.ysx_android.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.wei.ysx_android.MainActivity;
import com.wei.ysx_android.R;
import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.model.Goods;


public class GoodsAddDetailFragment extends Fragment {

    EditText code, goodsName, price, note;

    Button addByCode, manual;

    public static  MainActivity activity;

    ImageView imageView;

    public GoodsAddDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_add_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        code = getActivity().findViewById(R.id.editTextNumberDecimal);
        goodsName = getActivity().findViewById(R.id.editTextTextPersonName10);
        price = getActivity().findViewById(R.id.editTextTextPersonName11);
        note = getActivity().findViewById(R.id.editTextTextPersonName12);
        addByCode = getActivity().findViewById(R.id.button);
        manual = getActivity().findViewById(R.id.button3);
        imageView = getView().findViewById(R.id.imageView13);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZXingLibrary.initDisplayOpinion(getContext());
                Intent intent = new Intent(activity, CaptureActivity.class);
                startActivityForResult(intent, 333);
            }
        });
        addByCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsApi.getGoodsInfoByCode(code.getText().toString());
            }
        });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goods goods = new Goods();
                goods.setGoodsName(goodsName.getText().toString());
                goods.setNote(note.getText().toString());
                goods.setPrice(price.getText().toString());
                GoodsApi.addGoodsByManual(goods);
            }
        });
    }
}