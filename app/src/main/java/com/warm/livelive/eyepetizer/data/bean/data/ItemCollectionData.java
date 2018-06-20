package com.warm.livelive.eyepetizer.data.bean.data;

import com.warm.livelive.eyepetizer.data.bean.Content;
import com.warm.livelive.eyepetizer.data.bean.header.ItemCollectionHeader;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-17 15:22
 * 描述：
 */
public class ItemCollectionData extends BaseData{


    /**
     * header : {}
     * itemList : []
     * count : 5
     */

    private ItemCollectionHeader header;
    private int count;
    private List<Content> itemList;

    public ItemCollectionHeader getHeader() {
        return header;
    }

    public void setHeader(ItemCollectionHeader header) {
        this.header = header;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Content> getItemList() {
        return itemList;
    }

    public void setItemList(List<Content> itemList) {
        this.itemList = itemList;
    }

}
