package com.ryo.medata.util.util;

import java.util.Map;

/**
 * map 工具类
 * @author bbhou
 * @date 2017/8/1
 */
public class MapUtil {

    /**
     * 判断 map 为空
     * @param map 集合
     * @return {@code true} 为空
     */
    public static boolean isEmpty(Map<?,?> map) {
        return null == map || 0 == map.size();
    }

    /**
     * 判断 map 不为空
     * @param map 集合
     * @return {@code true} 不为空
     */
    public static boolean isNotEmpty(Map<?,?> map) {
        return !isEmpty(map);
    }

}
