package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-18 16:09
 * 描述：
 */
public class Shield {
    /**
     * itemType : author
     * itemId : 2163
     * shielded : false
     */

    private String itemType;
    private int itemId;
    private boolean shielded;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isShielded() {
        return shielded;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }
}
