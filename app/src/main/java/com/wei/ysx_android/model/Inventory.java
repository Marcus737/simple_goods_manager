package com.wei.ysx_android.model;

public class Inventory {

    private String id;
    private String goodsId;
    private String value;
    private Goods goods;

    public Inventory() {
    }

    public Inventory(String id, String goodsId, String value, Goods goods) {
        this.id = id;
        this.goodsId = goodsId;
        this.value = value;
        this.goods = goods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
