package com.wei.ysx_android;

import com.wei.ysx_android.http.GoodsApi;
import com.wei.ysx_android.i.CommonListener;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        GoodsApi goodsApi = new GoodsApi();
        goodsApi.listGoods(new CommonListener() {
            @Override
            public <T> void callBack(T t) {
                System.out.println(t);
            }
        });
        try {
            Thread.sleep(20000);
        }catch (Exception e){

        }
    }
}