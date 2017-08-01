package com.ryo.medata.util.util;

import java.util.Map;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MapUtil {

    /**
     * 判断map为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map) {
        return null == map || 0 == map.size();
    }


    public static boolean isNotEmpty(Map<?,?> map) {
        return !isEmpty(map);
    }

}
