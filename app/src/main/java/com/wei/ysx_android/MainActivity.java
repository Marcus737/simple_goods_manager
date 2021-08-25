package com.wei.ysx_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wei.ysx_android.fragment.GoodsAddDetailFragment;
import com.wei.ysx_android.fragment.SettingFragment;
import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.http.InventoryApi;
import com.wei.ysx_android.http.OrderApi;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Order;

public class MainActivity extends AppCompatActivity {

    ImageView i1,i2,i3,i4;
    NavController controller;
    public Order order;
    String[] allpermissions = new String[]{Manifest.permission.INTERNET,
            Manifest.permission.CAMERA, Manifest.permission.VIBRATE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applypermission();
        i1 = findViewById(R.id.imageView);
        i2 = findViewById(R.id.imageView2);
        i3 = findViewById(R.id.imageView3);
        i4 = findViewById(R.id.imageView4);
        setListener();
        controller = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, controller);
        GoodsApi.activity = this;
        GoodsAddDetailFragment.activity = this;
        InventoryApi.activity = this;
        OrderApi.activity = this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            Bundle bundle = data.getExtras();
            String code = bundle.getString(CodeUtils.RESULT_STRING);
            GoodsApi.getGoodsInfoByCode(code);
        }catch (Exception e){

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return controller.navigateUp();
    }

    private void setListener() {
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.goodsFragment);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.inventoryFragment);
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.orderFragment);
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.settingFragment);
            }
        });
    }

    public void applypermission(){
        if(Build.VERSION.SDK_INT>=23){
            boolean needapply=false;
            for(int i=0;i<allpermissions.length;i++){
                int chechpermission= ContextCompat.checkSelfPermission(getApplicationContext(),
                        allpermissions[i]);
                if(chechpermission!= PackageManager.PERMISSION_GRANTED){
                    needapply=true;
                }
            }
            if(needapply){
                ActivityCompat.requestPermissions(MainActivity.this,allpermissions,1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i=0;i<grantResults.length;i++){
            if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, permissions[i]+"已授权",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this,permissions[i]+"拒绝授权",Toast.LENGTH_SHORT).show();
            }
        }
    }

}