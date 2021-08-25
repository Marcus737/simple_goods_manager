package com.wei.ysx_android.model;

public class Goods {



    private Integer id;
    private String manuName;
    private String spec;
    private String trademark;
    private String goodsType;
    private String note;
    private String manuAddress;
    private boolean enable;
    private String goodsName;
    private String price;
    private String img;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManuName() {
        return manuName;
    }

    public void setManuName(String manuName) {
        this.manuName = manuName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getManuAddress() {
        return manuAddress;
    }

    public void setManuAddress(String manuAddress) {
        this.manuAddress = manuAddress;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Goods() {
    }

    public Goods(Integer id, String manuName, String spec, String trademark, String goodsType, String note, String manuAddress, boolean enable, String goodsName, String price, String img, String code) {
        this.id = id;
        this.manuName = manuName;
        this.spec = spec;
        this.trademark = trademark;
        this.goodsType = goodsType;
        this.note = note;
        this.manuAddress = manuAddress;
        this.enable = enable;
        this.goodsName = goodsName;
        this.price = price;
        this.img = img;
        this.code = code;
    }



    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", manuName='" + manuName + '\'' +
                ", spec='" + spec + '\'' +
                ", trademark='" + trademark + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", note='" + note + '\'' +
                ", manuAddress='" + manuAddress + '\'' +
                ", enable=" + enable +
                ", goodsName='" + goodsName + '\'' +
                ", price='" + price + '\'' +
                ", img='" + img + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
