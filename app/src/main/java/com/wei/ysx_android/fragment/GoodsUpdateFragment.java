package com.wei.ysx_android.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wei.ysx_android.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsUpdateFragment extends Fragment {

    public GoodsUpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_update, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}