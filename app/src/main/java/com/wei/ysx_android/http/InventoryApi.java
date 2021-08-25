package com.wei.ysx_android.http;

import android.util.Log;
import android.widget.Toast;
import com.google.gson.*;
import com.wei.ysx_android.MainActivity;
import com.wei.ysx_android.i.CommonListener;
import com.wei.ysx_android.model.Inventory;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class InventoryApi {

    private static final String TAG = "InventoryAPI";
    public static MainActivity activity;


    static String prefix = "http://159.75.38.125:8081";
    static String listInventoryUrl = "/inventory/list";
    static String updateInventoryUrl = "/inventory/updateInventory/goodsId=%s&value=%s";


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
        JsonObject object = element.getAsJsonObject();
        JsonElement code = object.get("code");

        if (!code.getAsString().equals("200")){
            Toast.makeText(activity, object.get("message").getAsString(), Toast.LENGTH_SHORT).show();
            return null;
        }
        JsonElement data = object.get("data");
        return (T) data;
    }

    public static void updateInventory(String goodsId, String value){
        updateInventoryUrl = String.format(updateInventoryUrl, goodsId, value);
        final Request request = new Request.Builder()
                .url(prefix + updateInventoryUrl)
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

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Toast.makeText(activity, getMsg(response.body().string()), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    public static void listInventory(final CommonListener listener){
        final Request request = new Request.Builder()
                .url(prefix + listInventoryUrl)
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
                ArrayList<Inventory> inventories = new ArrayList<>();
                JsonElement jsonElement = array.get(1);
                JsonArray array1 = jsonElement.getAsJsonArray();
                Gson gson = new Gson();
                for (JsonElement e : array1) {
                    Inventory inventory = gson.fromJson(e.toString(), Inventory.class);
                    inventories.add(inventory);
                }
                listener.callBack(inventories);
            }
        });
    }

}
