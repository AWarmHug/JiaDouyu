package com.warm.livelive.douyu.data.socket.nativesocek;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：warm
 * 时间：2017-12-27 12:57
 * 描述：
 */

public class MsgDecoder {
    private static final String TAG = "MsgDecoder";

    public static List<Map<String, Object>> decode(byte[] bytes) {
        if (bytes.length > 12) {
            String data = new String(bytes);
            return splitByType(data);
        } else {
            return null;
        }
    }


    /**
     * �������������������type@=loginres/userid@=0/roomgroup@=0/pg@=0/sessionid@=0/username@=/nickname@=/live_stat@=0/is_illegal@=0/ill_ct@=/ill_ts@=0/now@=0/ps@=0/es@=0/it@=0/its@=0/npv@=0/best_dlev@=0/cur_lev@=0/nrc@=4284481613/ih@=0/sid@=72963/sahf@=0/��
     * 消息长度：4*2 + 消息类型：2 + 加密字段 0+保留字段：0。
     * 头部一共有12个，加上尾部的'\0'一个
     * byte[] bytes2 = new byte[len - 12 - 1 ];
     * System.arraycopy(bytes, 12, bytes2, 0, len - 12 - 1 );
     * 额外还要处理 最后 / 如：key1@=value1/key2@=value2/key3@=value3/
     *
     * @return
     */


    /**
     * 可能是数组 所以需要先用type分割
     * �����������������type@=mrkl/���������������type@=chatmsg/rid@=249117/ct@=2/uid@=147357369/nn@=没心没肺没感觉的小孩/txt@=富勒键鼠 今晚吃鸡/cid@=64c7ab321ee448f9e99a1e0000000000/ic@=avanew@Sface@S201707@S25@S03@S0657155427f41841a9f23f7a16c54d5e/level@=12/sahf@=0/bnn@=/bl@=0/brid@=0/hc@=/el@=/��
     *
     * @param data
     * @return
     */
    public static List<Map<String, Object>> splitByType(String data) {
        //把头部的去掉，尾部的\0去打掉
        data = data.substring(12, data.length() - 1);

        List<Map<String, Object>> maps = new ArrayList<>();
        String items[] = appearNum(data, "type") > 1 ? split(data.split("type")) : new String[]{data};
        for (String item : items) {
            if (!TextUtils.isEmpty(item)) {
                maps.add(_decode(item));
            }
        }
        return maps;
    }

//        String str = "���������������\u0002����type@=chatmsg/rid@=249117/ct@=2/uid@=4204505/nn@=ty0689/txt@=请问下蛇哥是那个傻逼？一直请水军在这发弹幕/cid@=64c7ab321ee448f96cee210000000000/ic@=avatar@S004@S20@S45@S05_avatar/level@=6/sahf@=0/bnn@=/bl@=0/brid@=0/hc@=/el@=/��\u0018\u0001����\u0018\u0001�����\u0002����type@=chatmsg/rid@=249117/uid@=14306343/nn@=张子鱼/txt@=18杀吃鸡/cid@=64c7ab321ee448f96fee210000000000/ic@=avanew@Sface@S201611@S03@S18@S8087ebf10eb30839076a9347cb57a8ef/level@=10/sahf@=0/bnn@=小毒神/bl@=2/brid@=3480287/hc@=fa4e41550d829ff35f8c7208ee5b2e22/el@=/��";


    private static String[] split(String[] strings) {
        for (int i = 1; i < strings.length; i++) {
            strings[i] = "type"+strings[i].substring(0, (strings[i].length() - 12) > 0 ? strings[i].length() - 12 : 0);
        }
        return strings;
    }


    public static Map<String, Object> _decode(String pickData) {
        String items[] = pickData.substring(0, pickData.length() - 1 > 0 ? pickData.length() - 1 : 0).split("/");
        Map<String, Object> map = new ArrayMap<>();
        for (String item : items) {
            String key = item.substring(0, item.indexOf("@=") > 0 ? item.indexOf("@=") : 0);
            key = key.replaceAll("@S", "/").replaceAll("@A", "@");
            String valueStr = item.substring((item.indexOf("@=") + 2) > 0 ? item.indexOf("@=") + 2 : 0);
            valueStr = valueStr.replaceAll("@S", "/").replaceAll("@A", "@");
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(valueStr)) {
                map.put(key, valueStr);
            }
        }
        return map;

    }


    public static int appearNum(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }

    public static boolean ss(String a) {
        return TextUtils.isEmpty(a);

    }


}
