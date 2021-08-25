package com.wei.ysx_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.R;
import com.wei.ysx_android.model.GoodsWithNum;
import com.wei.ysx_android.mview.ImageViewWithURL;

import java.util.List;


public class OrderGoodsListAdapter extends RecyclerView.Adapter {

    public List<GoodsWithNum> goodsInfo;

    ImageViewWithURL goodsImg;
    TextView goodsName;
    TextView goodsPrice;
    TextView goodsNum;


    public OrderGoodsListAdapter(List<GoodsWithNum>  goodsInfo) {

        this.goodsInfo = goodsInfo;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_goods_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        findView(holder);
        GoodsWithNum goods = goodsInfo.get(position);
        goodsImg.setImageURL(goods.getGoods().getImg());
        goodsName.setText(goods.getGoods().getGoodsName());
        goodsPrice.setText(goods.getGoods().getPrice());
        goodsNum.setText(goods.getNum() + "个");

    }

    private void findView(RecyclerView.ViewHolder holder) {
        goodsImg = holder.itemView.findViewById(R.id.imageView141);
        goodsName = holder.itemView.findViewById(R.id.textView191);
        goodsPrice = holder.itemView.findViewById(R.id.textView201);
        goodsNum = holder.itemView.findViewById(R.id.textView49);
    }

    @Override
    public int getItemCount() {
        return goodsInfo.size();
    }


}
