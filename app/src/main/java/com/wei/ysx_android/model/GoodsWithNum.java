package com.wei.ysx_android.model;

public class GoodsWithNum {
    private Integer num;
    private Goods goods;

    public GoodsWithNum() {
    }

    public GoodsWithNum(Integer goodsNum, Goods goods) {
        this.num = goodsNum;
        this.goods = goods;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
