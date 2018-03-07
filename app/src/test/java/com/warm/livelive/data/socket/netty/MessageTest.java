package com.warm.livelive.data.socket.netty;

import org.junit.Before;
import org.junit.Test;

/**
 * 作者：warm
 * 时间：2018-03-06 15:55
 * 描述：
 */
public class MessageTest {
    private String msg;

    @Before
    public void setUp() throws Exception {
        msg="type@=loginres/userid@=0/roomgroup@=0/pg@=0/sessionid@=0/username@=/nickname@=/live_stat@=0/is_illegal@=0/ill_ct@=/ill_ts@=0/now@=0/ps@=0/es@=0/it@=0/its@=0/npv@=0/best_dlev@=0/cur_lev@=0/nrc@=2821921416/ih@=0/sid@=72963/sahf@=0/sceneid@=0/";
    }

    @Test
    public void getMap() throws Exception {
        Message message=new Message(msg);

        System.out.println(message.getMap().size());
    }

}