package com.wei.ysx_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.fragment.OrderAddDetailFragment;
import com.wei.ysx_android.i.AdapterAndFragmentListener;
import com.wei.ysx_android.model.Goods;
import com.wei.ysx_android.mview.ImageViewWithURL;

import java.util.*;


public class OrderAddDetailAdapter extends RecyclerView.Adapter {

    public List<Goods> goodsInfo;

    ImageViewWithURL img;

    TextView goodsName, goodsPrice;
    ImageButton add, delete;
    public static Map<String, List> map;
    AdapterAndFragmentListener listener;


    public OrderAddDetailAdapter(List<Goods> goodsInfo, AdapterAndFragmentListener listener) {
        this.goodsInfo = goodsInfo;
        map = new HashMap<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_list_item_in_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        findView(holder);
        final Goods goods = goodsInfo.get(position);
        img.setImageURL(goods.getImg());
        goodsName.setText(goods.getGoodsName());
        goodsPrice.setText(goods.getPrice());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = goods.getGoodsName();
                if (map.containsKey(name)){
                    List t = map.get(name);
                    t.set(1, (int)t.get(1) + 1);
                }else {
                    ArrayList list = new ArrayList<>();
                    list.add(goods.getCode());
                    list.add(1);
                    map.put(name, list);
                }
                displaySelected(map);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = goods.getGoodsName();
                if (map.containsKey(name)){
                    Integer num = (int)map.get(name).get(1);
                    if (num == 0){
                        map.remove(name);
                        return;
                    }
                    List list = map.get(name);
                    list.set(1, (int)list.get(1) - 1);
                }else {
                    return;
                }
                displaySelected(map);
            }
        });

    }



    private void displaySelected(Map<String, List> map) {
        Set<Map.Entry<String, List>> entries = map.entrySet();
        StringBuilder sb = new StringBuilder();
        float price = 0;
        for (Map.Entry<String, List> entry : entries) {
            sb.append(entry.getKey()).append(":").append("\t").append(entry.getValue().get(1)).append("个").append("\n").append("\n");
            for (Goods goods : OrderAddDetailFragment.backup) {
                if (goods.getGoodsName().equals(entry.getKey())){
                    price += Float.parseFloat(goods.getPrice()) * (int)entry.getValue().get(1);
                }
            }
        }
        listener.onClick(sb.toString(), String.valueOf(price));
    }

    private void findView(RecyclerView.ViewHolder holder) {
        img = holder.itemView.findViewById(R.id.imageViewWithURL);
        goodsName = holder.itemView.findViewById(R.id.textView40);
        goodsPrice = holder.itemView.findViewById(R.id.textView41);
        add = holder.itemView.findViewById(R.id.imageButton);
        delete = holder.itemView.findViewById(R.id.imageButton2);
    }

    @Override
    public int getItemCount() {
        return goodsInfo.size();
    }
}
