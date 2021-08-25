package com.wei.ysx_android.fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wei.ysx_android.R;
import com.wei.ysx_android.http.InventoryApi;


public class InventoryDetailFragment extends Fragment {

    public InventoryDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_inventory_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null){
            String goodsName = getArguments().getString("goodsName");
            final String goodsNum = getArguments().getString("goodsNum");
            final String goodsId = getArguments().getString("goodsId");
            TextView gName = this.getActivity().findViewById(R.id.textView25);
            final EditText editText = getActivity().findViewById(R.id.editText2);


            gName.setText(goodsName);
            editText.setText(String.valueOf(goodsNum));

            Button b = getActivity().findViewById(R.id.button2);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InventoryApi.updateInventory(goodsId, editText.getText().toString());
                }
            });
        }
    }
}