package com.wei.ysx_android.http;

import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.google.gson.*;
import com.wei.ysx_android.MainActivity;
import com.wei.ysx_android.R;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.CreateBillArgument;
import com.wei.ysx_android.model.Inventory;
import com.wei.ysx_android.model.Order;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderApi {

    private static final String TAG = "OrderAPI";
    public static MainActivity activity;


    static String prefix = "http://159.75.38.125:8081/bill";
    static String listOrderUrl = "/list";
    static String updateOrderUrl = "/createBill";
    static String noPayOrderUrl = "/noPayBills";
    static String payUrl = "/pay/%s";
    static String cancelPayUrl = "/cancelPay/%s";
    static String createOrderUrl = "/createBill";
    static String incomeUrl = "/getIncome";

    public static void getIncome(final CommonListener listener){
        final Request request = new Request.Builder()
                .url(prefix + incomeUrl)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonElement e2 = getData(response.body().string());
                if (e2 == null || e2.isJsonNull()){
                    return;
                }
                listener.callBack(e2.getAsString());
            }
        });
    }

    static OkHttpClient okHttpClient = new OkHttpClient();

    public static String getMsg(String json){
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(json);
        JsonObject object = element.getAsJsonObject();
        JsonElement msg = object.get("message");
        return msg.toString();
    }

    public static <T> T getData(String json){
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(json);
        final JsonObject object = element.getAsJsonObject();
        JsonElement code = object.get("code");
        if (!code.getAsString().equals("200")){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, object.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
        JsonElement data = object.get("data");
        return (T) data;
    }

    public static void createOrder(String[] goodsCodes, Integer[] goodsNums){
        Gson gson = new Gson();
        CreateBillArgument argument = new CreateBillArgument();
        argument.setGoodsCodes(goodsCodes);
        argument.setNums(goodsNums);
        String s = gson.toJson(argument);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, s);
        final Request request = new Request.Builder()
                .url(prefix + createOrderUrl)
                .put(body)//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final JsonElement e2 = getData(response.body().string());
                if (e2 == null){
                    return;
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "创建订单成功", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                        dialog.setTitle("重要");
                        dialog.setMessage("订单创建成功，是否已收款？");
                        dialog.setCancelable(false);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updatePayState(e2.getAsString(), true);
                            }
                        });
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    }
                });
            }
        });
    }

    public static void noPayOrder(final CommonListener listener){
        final Request request = new Request.Builder()
                .url(prefix + noPayOrderUrl)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonElement e2 = getData(response.body().string());
                if (e2 == null){
                    return;
                }
                JsonArray array = e2.getAsJsonArray();
                ArrayList<Order> orders = new ArrayList<>();
                JsonElement jsonElement = array.get(1);
                JsonArray array1 = jsonElement.getAsJsonArray();
                Gson gson = new Gson();
                for (JsonElement e : array1) {
                    Order order = gson.fromJson(e.toString(), Order.class);
                    orders.add(order);
                }
                listener.callBack(orders);
            }
        });
    }

    public static void updatePayState(String billId, boolean enable){
        String url;
        if (enable){
            url = String.format(payUrl, billId);
        }else {
            url = String.format(cancelPayUrl, billId);
        }
        final Request request = new Request.Builder()
                .url(prefix + url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                JsonElement e2 = getData(response.body().string());
                if (e2 == null){
                    return;
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "更新成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void listOrder(final CommonListener listener){
        final Request request = new Request.Builder()
                .url(prefix + listOrderUrl)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonElement e2 = getData(response.body().string());
                if (e2 == null){
                    return;
                }
                JsonArray array = e2.getAsJsonArray();
                ArrayList<Order> orders = new ArrayList<>();
                JsonElement jsonElement = array.get(1);
                JsonArray array1 = jsonElement.getAsJsonArray();
                Gson gson = new Gson();
                for (JsonElement e : array1) {
                    Order order = gson.fromJson(e.toString(), Order.class);
                    orders.add(order);
                }
                listener.callBack(orders);
            }
        });
    }

}
