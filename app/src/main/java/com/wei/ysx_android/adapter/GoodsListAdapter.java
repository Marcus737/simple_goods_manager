package com.wei.ysx_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.fragment.GoodsDetailFragment;
import com.wei.ysx_android.fragment.GoodsListFragment;
import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Goods;
import com.wei.ysx_android.mview.ImageViewWithURL;

import java.util.ArrayList;
import java.util.List;


class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }
}

public class GoodsListAdapter extends RecyclerView.Adapter{

    public static   List<Goods> goodsInfo;

    ImageViewWithURL goodsImg;
    TextView goodsName;
    TextView goodsPrice;


    public GoodsListAdapter() {

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_list_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        findView(holder);
        final Goods goods = goodsInfo.get(position);
        goodsImg.setImageURL(goods.getImg());
        goodsName.setText(goods.getGoodsName());
        goodsPrice.setText(goods.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsDetailFragment.goods = goods;
                Navigation.findNavController(holder.itemView).navigate(R.id.action_goodsListFragment_to_goodsDetailFragment);
            }
        });

    }



    private void findView(RecyclerView.ViewHolder holder) {
        goodsImg = holder.itemView.findViewById(R.id.imageView14);
        goodsName = holder.itemView.findViewById(R.id.textView19);
        goodsPrice = holder.itemView.findViewById(R.id.textView20);
    }

    @Override
    public int getItemCount() {
        return goodsInfo.size();
    }
}
