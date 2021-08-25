package com.wei.ysx_android.model;




public class CreateBillArgument {

    String[] goodsCodes;
    Integer[] nums;


    public CreateBillArgument() {
    }

    public String[] getGoodsCodes() {
        return goodsCodes;
    }

    public void setGoodsCodes(String[] goodsCodes) {
        this.goodsCodes = goodsCodes;
    }

    public Integer[] getNums() {
        return nums;
    }

    public void setNums(Integer[] nums) {
        this.nums = nums;
    }
}
