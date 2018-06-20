package com.warm.livelive.douyu.data.bean.live;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-12 10:47
 * 描述：
 */
public class LiveCate1 implements Parcelable {


    /**
     * cate1_id : 1
     * cate_name : 网游竞技
     * cate2_count : 76
     * cate2_list : [{"cate2_id":1,"cate2_name":"英雄联盟","cate2_short_name":"LOL","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/d3e0073bfb714186ab0c818744601963.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/ffdc72ed97b50ad8011de9a779b8d83b.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/78f61a979f3f80a3b8d4f017dffd5944.png"},{"cate2_id":347,"cate2_name":"堡垒之夜","cate2_short_name":"blzy","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/10253417ea81474dee99121655f5f8be.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/9b473b98d4e3ae103816c200cce85174.jpg","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/b28480f694ab62542de34fe4fb77e999.png"},{"cate2_id":3,"cate2_name":"DOTA2","cate2_short_name":"DOTA2","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/a887fa9dc9d6901b5fd5c86c8e169436.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/1fafd83196c5a75351fb9422c2e57d21.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/012022884d76403b57be9bd95260db99.png"},{"cate2_id":2,"cate2_name":"炉石传说","cate2_short_name":"How","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/26d993d79c1daa53d1b083980e97559e.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/39cf38b099203cfdb730bd9bf5c928cf.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/e3192bb14a96a09b6d6c0d49b0765ef3.png"},{"cate2_id":40,"cate2_name":"DNF","cate2_short_name":"DNF","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/0cd803160cfc26acfd0831dfe7b3de92.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/c9a897beb1f21a6ffbbc93d97956b68f.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/bee56ea80cf358bb9efc51f8ac4c7b05.png"},{"cate2_id":33,"cate2_name":"穿越火线","cate2_short_name":"CF","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/94691f7a259e7e85c4c65e5849cd94dc.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/6eecb81635b03ebc9272abe9216f6d21.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/fd8dcffafac4cb6c5621b8b6bcb272e7.png"},{"cate2_id":434,"cate2_name":"逆水寒","cate2_short_name":"nsh","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/a2473b2f8bdd65db982c50550a92c128.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/52fa1388d43a6be39fc1d9f2c2f33565.jpg","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/d251ef7555dc4ef3dbc7bb6be50c6472.png"},{"cate2_id":5,"cate2_name":"魔兽世界","cate2_short_name":"WOW","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/b6ae05443687a28ab177e3ff17de1590.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/ad261c282823bd3e06aa8f613dedee14.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/a37ee3d810c9588e2378dfbe26b31275.png"},{"cate2_id":429,"cate2_name":"罗博造造","cate2_short_name":"lbzz","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/2dcb4649553c347890e5110ecd91ef50.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/2c00180f40444c6fa41fbdc01b1f2a9a.jpg","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/90ed8a80b5bb4919f5e09fd739bb4c25.png"},{"cate2_id":203,"cate2_name":"热门网游","cate2_short_name":"rmwy","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/801b8750f0a509fd0b0c43139c7ab20b.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/45d9411d253db2d71e58e1fa248cac90.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/5454f63867af5a5956fc5c2350c5022a.png"},{"cate2_id":217,"cate2_name":"DOTA","cate2_short_name":"DOTA","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/e7bbe8818f8da82e35e5b69c7c4321c7.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/29e04a3468c119755cf5380fe61cb856.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/d48017e3d7fe5628a385f9d3a98dc300.png"},{"cate2_id":6,"cate2_name":"CS:GO","cate2_short_name":"CSGO","push_nearby":0,"is_vertical":0,"icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/180ac281ab3f980bbfdff366d9636072.jpg","small_icon_url":"https://cs-op.douyucdn.cn/dycatr/game_cate/5210286dd2410057a91007106d8d531c.png","square_icon_url":"https://cs-op.douyucdn.cn/dycatr/7e00486f58f90feb8351073477a4c96d.png"}]
     */

    private int cate1_id;
    private String cate_name;
    private int cate2_count;
    private List<LiveCate2> cate2_list;

    public int getCate1_id() {
        return cate1_id;
    }

    public void setCate1_id(int cate1_id) {
        this.cate1_id = cate1_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getCate2_count() {
        return cate2_count;
    }

    public void setCate2_count(int cate2_count) {
        this.cate2_count = cate2_count;
    }

    public List<LiveCate2> getCate2_list() {
        return cate2_list;
    }

    public void setCate2_list(List<LiveCate2> cate2_list) {
        this.cate2_list = cate2_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cate1_id);
        dest.writeString(this.cate_name);
        dest.writeInt(this.cate2_count);
        dest.writeTypedList(this.cate2_list);
    }

    public LiveCate1() {
    }

    protected LiveCate1(Parcel in) {
        this.cate1_id = in.readInt();
        this.cate_name = in.readString();
        this.cate2_count = in.readInt();
        this.cate2_list = in.createTypedArrayList(LiveCate2.CREATOR);
    }

    public static final Parcelable.Creator<LiveCate1> CREATOR = new Parcelable.Creator<LiveCate1>() {
        @Override
        public LiveCate1 createFromParcel(Parcel source) {
            return new LiveCate1(source);
        }

        @Override
        public LiveCate1[] newArray(int size) {
            return new LiveCate1[size];
        }
    };
}
