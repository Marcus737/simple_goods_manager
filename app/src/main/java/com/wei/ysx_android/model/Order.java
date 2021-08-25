package com.wei.ysx_android.model;

import java.util.List;


public class Order {

    private String id;
    private String createTime;
    private String state;

    private List<GoodsWithNum> goodsWithNumList;

    public Order() {
    }

    public Order(String id, String createTime, String state, List<GoodsWithNum> goods) {
        this.id = id;
        this.createTime = createTime;
        this.state = state;
        this.goodsWithNumList = goods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<GoodsWithNum> getGoodsWithNumList() {
        return goodsWithNumList;
    }

    public void setGoodsWithNumList(List<GoodsWithNum> goodsWithNumList) {
        this.goodsWithNumList = goodsWithNumList;
    }


}
