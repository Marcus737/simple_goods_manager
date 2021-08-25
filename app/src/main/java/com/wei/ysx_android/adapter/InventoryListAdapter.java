package com.wei.ysx_android.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.model.Inventory;
import com.wei.ysx_android.mview.ImageViewWithURL;

import java.util.ArrayList;
import java.util.List;


public class InventoryListAdapter extends RecyclerView.Adapter {

    public static List<Inventory> inventories;

    ImageViewWithURL goodsImg;
    TextView goodsName;
    TextView goodsNum;

    public InventoryListAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_list_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        findView(holder);
        final Inventory inventory = inventories.get(position);
        goodsImg.setImageURL(inventory.getGoods().getImg());
        goodsName.setText(inventory.getGoods().getGoodsName());
        goodsNum.setText(inventory.getValue());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = holder.getAdapterPosition();
                Inventory i2 = inventories.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("goodsName", i2.getGoods().getGoodsName());
                bundle.putString("goodsNum", i2.getValue());
                bundle.putString("goodsId", i2.getGoodsId());
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_inventoryListFragment_to_inventoryDetailFragment, bundle);
            }
        });
    }

    private void findView(RecyclerView.ViewHolder holder) {
        goodsImg = holder.itemView.findViewById(R.id.inventory_goods_list_item_imageView14);
        goodsName = holder.itemView.findViewById(R.id.inventory_goods_list_item_textView19);
        goodsNum = holder.itemView.findViewById(R.id.inventory_goods_list_item_textView20);
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }
}
