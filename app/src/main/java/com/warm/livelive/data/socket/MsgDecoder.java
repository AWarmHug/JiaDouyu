package com.warm.livelive.data.socket;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：warm
 * 时间：2017-12-27 12:57
 * 描述：
 */

public class MsgDecoder {


    public static List<Map<String, Object>> decode(byte[] bytes) {
        if (bytes.length > 12) {
            String data = new String(bytes);
            return splitByType(data);
        } else {
            return null;
        }
    }

    public static List<Map<String, Object>> decode(String data) {
        if (data.length() > 12) {
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
    private static String pick(String data) {
        Log.d("####", "pick: " + data);
        data = data.substring(12, data.length() - 1);
        return data;
    }

    /**
     * 可能是数组 所以需要先用type分割
     *
     * @param data
     * @return
     */
    private static List<Map<String, Object>> splitByType(String data) {
        data = data;
        List<Map<String, Object>> maps = new ArrayList<>();
        String items[] = appearNum(data, "type") > 1 ? data.split("type") : new String[]{data};
        for (String item : items) {
            maps.add(_decode(pick(item)));
        }
        return maps;

    }

    public static Map<String, Object> _decode(String pickData) {
        Log.d("####", "_decode: " + pickData);
        String items[] = pickData.split("/");
        Map<String, Object> map = new ArrayMap<>();
        for (String item : items) {
            String key = item.substring(0, item.indexOf("@=") > 0 ? item.indexOf("@=") : 0);
            key = key.replaceAll("@S", "/").replaceAll("@A", "@");
            String valueStr = item.substring((item.indexOf("@=") + 2) > 0 ? item.indexOf("@=") + 2 : 0);
            valueStr = valueStr.replaceAll("@S", "/").replaceAll("@A", "@");
            map.put(key, valueStr);
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


}
