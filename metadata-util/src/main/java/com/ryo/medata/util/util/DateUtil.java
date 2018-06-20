package com.ryo.medata.util.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author bbhou
 * @date 2017/8/1
 */
public class DateUtil {

    private static final String DATE_TIME_FORMAT = "YYYY-MM-dd HH:mm:ss.SSS";


    /**
     * 获取日期对应的字符串
     * @param date 日期
     * @return 对应的字符串
     */
    public static String getDateStr(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        return dateFormat.format(date);
    }

}
