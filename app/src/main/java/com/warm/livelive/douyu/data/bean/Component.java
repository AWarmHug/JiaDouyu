package com.warm.livelive.douyu.data.bean;

import java.util.Objects;

/**
 * 作者：warm
 * 时间：2018-06-14 09:44
 * 描述：
 */
public class Component {
    public static final Component LIVE =getLive();

    private static Component getLive(){
        Component component=new Component();
        component.setCate2_id(-1);
        component.setComponent_id(-1);
        component.setTitle("直播");
        component.setIs_tab(1);
        return component;
    }

    /**
     * cate2_id : 350
     * component_id : 9
     * title : 视频推荐
     * is_tab : 1
     * conf : {"vod_cate_type":2,"vcid1":51,"vcid2":138}
     */

    private int cate2_id;
    private int component_id;
    private String title;
    private int is_tab;
    private Conf conf;

    public int getCate2_id() {
        return cate2_id;
    }

    public void setCate2_id(int cate2_id) {
        this.cate2_id = cate2_id;
    }

    public int getComponent_id() {
        return component_id;
    }

    public void setComponent_id(int component_id) {
        this.component_id = component_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_tab() {
        return is_tab;
    }

    public void setIs_tab(int is_tab) {
        this.is_tab = is_tab;
    }

    public Conf getConf() {
        return conf;
    }

    public void setConf(Conf conf) {
        this.conf = conf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(title, component.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title);
    }
}
