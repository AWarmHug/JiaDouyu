package com.warm.livelive.douyu.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-14 13:27
 * 描述：
 */
public class Promotion {
    /**
     * id : 99
     * cate2_id : 181
     * type : 11
     * banner : https://cs-op.douyucdn.cn/mgcps/2018/05/02/7c2d29924f36a5f402ca6a011b41054a.jpg
     * url_type : 1
     * url : https://apiv2.douyucdn.cn/H5/Mgame/appDetail?appId=10097&recId=796&type=promo
     * app_download_url : http://dmgame.douyucdn.cn/raw_pkg/10097_0_1.34.1.23_1527475202.apk
     * app_id : 10097
     * app_package_name : com.tencent.tmgp.sgame
     * app_name : 王者荣耀
     * icon : https://shark.douyucdn.cn/res/main/mobile_game_admin/ac81534eae50fda6226f9dc921c14d9e3f23084f.jpg
     * app_size : 889
     * app_downloads : 19011203
     * app_cid2_name : 竞技
     * is_cps : 1
     * cdn_url : http://dmgame.douyucdn.cn/raw_pkg/10097_0_1.34.1.23_1527475202.apk
     */

    private int id;
    private int cate2_id;
    private int type;
    private String banner;
    private int url_type;
    private String url;
    private String app_download_url;
    private int app_id;
    private String app_package_name;
    private String app_name;
    private String icon;
    private int app_size;
    private int app_downloads;
    private String app_cid2_name;
    private int is_cps;
    private String cdn_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCate2_id() {
        return cate2_id;
    }

    public void setCate2_id(int cate2_id) {
        this.cate2_id = cate2_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getUrl_type() {
        return url_type;
    }

    public void setUrl_type(int url_type) {
        this.url_type = url_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApp_download_url() {
        return app_download_url;
    }

    public void setApp_download_url(String app_download_url) {
        this.app_download_url = app_download_url;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getApp_package_name() {
        return app_package_name;
    }

    public void setApp_package_name(String app_package_name) {
        this.app_package_name = app_package_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getApp_size() {
        return app_size;
    }

    public void setApp_size(int app_size) {
        this.app_size = app_size;
    }

    public int getApp_downloads() {
        return app_downloads;
    }

    public void setApp_downloads(int app_downloads) {
        this.app_downloads = app_downloads;
    }

    public String getApp_cid2_name() {
        return app_cid2_name;
    }

    public void setApp_cid2_name(String app_cid2_name) {
        this.app_cid2_name = app_cid2_name;
    }

    public int getIs_cps() {
        return is_cps;
    }

    public void setIs_cps(int is_cps) {
        this.is_cps = is_cps;
    }

    public String getCdn_url() {
        return cdn_url;
    }

    public void setCdn_url(String cdn_url) {
        this.cdn_url = cdn_url;
    }
}
