package com.wei.ysx_android.fragment;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wei.ysx_android.R;
import com.wei.ysx_android.http.OrderApi;
import com.wei.ysx_android.i.CommonListener;


public class SettingFragment extends Fragment {

    TextView income;
    public static String incomeValue;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        income = getActivity().findViewById(R.id.textView50);
        OrderApi.getIncome(new CommonListener() {
            @Override
            public <T> void callBack(final T t) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null == t){
                            return;
                        }
                        String t1 = (String) t;
                        String s = t1.substring(0, t1.indexOf(".") + 2) + "元";
                        SettingFragment.incomeValue = s;
                        income.setText(incomeValue);
                    }
                });
            }
        });
    }
}