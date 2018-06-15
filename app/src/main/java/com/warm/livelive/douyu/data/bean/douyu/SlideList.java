package com.warm.livelive.douyu.data.bean.douyu;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-14 13:08
 * 描述：
 */
public class SlideList {
    private int num;
    private int count;
    private List<Slide> slide_list;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Slide> getSlide_list() {
        return slide_list;
    }

    public void setSlide_list(List<Slide> slide_list) {
        this.slide_list = slide_list;
    }

}
