package com.warm.livelive.eyepetizer.data.bean.data;

import com.warm.livelive.eyepetizer.data.bean.Content;
import com.warm.livelive.eyepetizer.data.bean.header.FollowCardHeader;

/**
 * 作者：warm
 * 时间：2018-06-18 15:31
 * 描述：
 */
public class FollowCardData extends BaseData {

    /**
     * dataType : FollowCard
     * header : {}
     * content : {}
     */

    private FollowCardHeader header;
    private Content content;


    public FollowCardHeader getHeader() {
        return header;
    }

    public void setHeader(FollowCardHeader header) {
        this.header = header;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
