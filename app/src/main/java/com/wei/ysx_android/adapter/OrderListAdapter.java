package com.wei.ysx_android.adapter;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.wei.ysx_android.MainActivity;
import com.wei.ysx_android.R;
import com.wei.ysx_android.fragment.OrderDetailFragment;
import com.wei.ysx_android.model.Order;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter {



    public static List<Order> orders;

    TextView createTime;
    TextView state;
    Activity activity;

    public OrderListAdapter(Activity activity) {

        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        findView(holder);
        final Order order = orders.get(position);
        createTime.setText(order.getCreateTime());
        if (order.getState().equals("0")){
            state.setText("未支付");
        }else {
            state.setText("已支付");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity at = (MainActivity) OrderListAdapter.this.activity;
                at.order = order;
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.orderDetailFragment);
            }
        });
    }

    private void findView(RecyclerView.ViewHolder holder) {
        createTime  = holder.itemView.findViewById(R.id.createTimeTextView);
        state = holder.itemView.findViewById(R.id.stateTextView);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
