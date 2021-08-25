package com.wei.ysx_android.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.adapter.OrderAddDetailAdapter;
import com.wei.ysx_android.decoration.MyDividerItemDecoration;
import com.wei.ysx_android.http.OrderApi;
import com.wei.ysx_android.i.AdapterAndFragmentListener;
import com.wei.ysx_android.model.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class OrderAddDetailFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    EditText searchInput;
    Button confirmCreateOrder;

    TextView selectedText, priceText;
    public static List<Goods> backup;


    public OrderAddDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_add_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                OrderAddDetailAdapter oaa = (OrderAddDetailAdapter)adapter;
                List<Goods> goodsInfo = oaa.goodsInfo;
                String text = s.toString();
                if (text.replaceAll(" ", "").equals("")){
                    goodsInfo.addAll(backup);
                    oaa.notifyDataSetChanged();
                    return;
                }
                goodsInfo.clear();
                for (Goods curGoods : backup) {
                    if (curGoods.getGoodsName().contains(text)){
                        goodsInfo.add(curGoods);
                    }
                }
                oaa.notifyDataSetChanged();
            }
        });

        confirmCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, List> map = OrderAddDetailAdapter.map;
                if (map.isEmpty()){
                    return;
                }
                String[] goodsCodes = new String[map.size()];
                Integer[] nums = new Integer[map.size()];
                Set<Map.Entry<String, List>> entries = map.entrySet();
                int i = 0;
                for (Map.Entry<String, List> entry : entries) {
                    goodsCodes[i] = (String) entry.getValue().get(0);
                    nums[i] = (int)entry.getValue().get(1);
                    ++i;
                }
                OrderApi.createOrder(goodsCodes, nums);
            }
        });
    }



    private void initView() {
        RecyclerView rv = this.getActivity().findViewById(R.id.orderAddRecycleView);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        selectedText = getActivity().findViewById(R.id.textView39);
        priceText = getActivity().findViewById(R.id.textView42);
        searchInput = getActivity().findViewById(R.id.editTextTextPersonName13);
        confirmCreateOrder = getActivity().findViewById(R.id.button5);

    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ArrayList<Goods> temp = new ArrayList<>();
        temp.addAll(backup);
        adapter = new OrderAddDetailAdapter(temp, new AdapterAndFragmentListener() {
            @Override
            public void onClick(String text, String price) {
                selectedText.setText(text);
                priceText.setText("共需" + price + "元");
            }
        });
    }

}