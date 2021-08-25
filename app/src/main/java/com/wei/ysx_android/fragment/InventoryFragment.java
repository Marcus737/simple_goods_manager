package com.wei.ysx_android.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.wei.ysx_android.R;
import com.wei.ysx_android.adapter.InventoryListAdapter;
import com.wei.ysx_android.http.InventoryApi;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Inventory;

import java.util.List;


public class InventoryFragment extends Fragment {



    public InventoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View allInventory = this.getActivity().findViewById(R.id.imageView8);
        final NavController controller = Navigation.findNavController(allInventory);
        s(controller, allInventory);
        View updateInventory = this.getActivity().findViewById(R.id.imageView9);
        s(controller, updateInventory);
    }

    private void s(final NavController controller, View updateInventory) {
        updateInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryApi.listInventory(new CommonListener() {
                    @Override
                    public <T> void callBack(final T t) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                InventoryListAdapter.inventories = (List<Inventory>) t;
                                controller.navigate(R.id.action_inventoryFragment_to_inventoryListFragment);
                            }
                        });
                    }
                });
            }
        });
    }
}