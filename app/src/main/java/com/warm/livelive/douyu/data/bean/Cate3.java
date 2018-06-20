package com.warm.livelive.douyu.data.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：warm
 * 时间：2018-06-14 12:59
 * 描述：
 */
public class Cate3 {
    public static final Cate3 ALL = getAll();

    private static final Cate3 getAll() {
        Cate3 cate3 = new Cate3();
        cate3.name = "全部";
        cate3.cate_id = 0;
        return cate3;
    }


    /**
     * id : 937
     * name : 金牌陪玩
     */
    @SerializedName("id")
    private int cate_id;
    private String name;

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
