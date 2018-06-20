package com.warm.livelive.eyepetizer.data.bean;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-17 15:02
 * 描述：
 */
public class TabInfo extends BaseBean {


    /**
     * tabInfo : {"tabList":[{"id":-1,"name":"发现","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/discovery"},{"id":-2,"name":"推荐","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0"},{"id":-3,"name":"日报","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/feed"},{"id":14,"name":"广告","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/14"},{"id":36,"name":"生活","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/36"},{"id":10,"name":"动画","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/10"},{"id":28,"name":"搞笑","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/28"},{"id":4,"name":"开胃","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/4"},{"id":2,"name":"创意","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/2"},{"id":18,"name":"运动","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/18"},{"id":20,"name":"音乐","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/20"},{"id":26,"name":"萌宠","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/26"},{"id":12,"name":"剧情","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/12"},{"id":32,"name":"科技","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/32"},{"id":6,"name":"旅行","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/6"},{"id":8,"name":"影视","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/8"},{"id":22,"name":"记录","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/22"},{"id":30,"name":"游戏","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/30"},{"id":38,"name":"综艺","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/38"},{"id":24,"name":"时尚","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/24"},{"id":34,"name":"集锦","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/34"}],"defaultIdx":1}
     */

    private TabInfoBean tabInfo;

    public TabInfoBean getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfoBean tabInfo) {
        this.tabInfo = tabInfo;
    }

    public static class TabInfoBean {
        /**
         * tabList : [{"id":-1,"name":"发现","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/discovery"},{"id":-2,"name":"推荐","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0"},{"id":-3,"name":"日报","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/feed"},{"id":14,"name":"广告","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/14"},{"id":36,"name":"生活","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/36"},{"id":10,"name":"动画","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/10"},{"id":28,"name":"搞笑","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/28"},{"id":4,"name":"开胃","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/4"},{"id":2,"name":"创意","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/2"},{"id":18,"name":"运动","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/18"},{"id":20,"name":"音乐","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/20"},{"id":26,"name":"萌宠","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/26"},{"id":12,"name":"剧情","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/12"},{"id":32,"name":"科技","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/32"},{"id":6,"name":"旅行","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/6"},{"id":8,"name":"影视","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/8"},{"id":22,"name":"记录","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/22"},{"id":30,"name":"游戏","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/30"},{"id":38,"name":"综艺","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/38"},{"id":24,"name":"时尚","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/24"},{"id":34,"name":"集锦","apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/category/34"}]
         * defaultIdx : 1
         */

        private int defaultIdx;
        private List<Tab> tabList;

        public int getDefaultIdx() {
            return defaultIdx;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public List<Tab> getTabList() {
            return tabList;
        }

        public void setTabList(List<Tab> tabList) {
            this.tabList = tabList;
        }

    }

}
