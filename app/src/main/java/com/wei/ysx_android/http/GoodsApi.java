package com.wei.ysx_android.http;

import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.wei.ysx_android.MainActivity;
import com.wei.ysx_android.adapter.GoodsListAdapter;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Goods;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoodsApi {

    private static final String TAG = "GoodsAPI";
    public static MainActivity activity;


    static String prefix = "http://159.75.38.125:8081";
    static String addGoodsByManualUrl = "/goods/addGoodsByManual";
    static String getGoodsInfoByCodeUrl = "/goods/getGoodsInfoByCode/code=";
    static String listGoodsUrl = "/goods/listGoods";
    static String updateGoodsByCode = "/goods/updateGoodsByCode";
    static OkHttpClient okHttpClient = new OkHttpClient();



    public static <T> T getData(String json){
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(json);
        JsonObject object = element.getAsJsonObject();
        JsonElement code = object.get("code");

        if (!code.getAsString().equals("200")){
            Toast.makeText(activity, object.get("message").getAsString(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return (T) object.get("data");
    }

    public static void updateGoodsByCode(Goods goods){

        Gson gson = new Gson();
        String s = gson.toJson(goods);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),s);

        final Request request = new Request.Builder()
                .url(prefix + updateGoodsByCode)
                .put(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(activity, "商品信息更新失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "更新成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void listGoods(final CommonListener listener){
        final Request request = new Request.Builder()
                .url(prefix + listGoodsUrl)
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
                JsonElement data = getData(response.body().string());
                JsonArray array =data.getAsJsonArray();
                if (array == null){
                    return;
                }
                ArrayList<Goods> goods = new ArrayList<>();
                JsonElement jsonElement = array.get(1);
                JsonArray array1 = jsonElement.getAsJsonArray();
                Gson gson = new Gson();

                for (JsonElement e : array1) {
                    Goods goods1 = gson.fromJson(e.toString(), Goods.class);
                    goods.add(goods1);
                }
                listener.callBack(goods);
            }
        });
    }

    public static void getGoodsInfoByCode(String code){

        final Request request = new Request.Builder()
                .url(prefix + getGoodsInfoByCodeUrl + code)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "添加成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void addGoodsByManual(Goods goods){
        Gson gson = new Gson();
        String s = gson.toJson(goods);
        RequestBody requestBody =FormBody.create(MediaType.parse("application/json; charset=utf-8"),s);
        final Request request = new Request.Builder()
                .url(prefix + addGoodsByManualUrl)
                .post(requestBody)//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Object data = null;
                        try {
                            data = getData(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (data != null){
                            Toast.makeText(activity, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
