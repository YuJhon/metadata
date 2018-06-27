package com.ryo.medata.util.util;

/**
 * 字符串-工具类
 * @author bbhou
 * @date 2017/7/31
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static boolean isEmpty(final String string) {
        return null == string || EMPTY.equals(string) || EMPTY.equals(string.trim());
    }

    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 根据任意多的空格进行分割字符串。
     * 1. 入参为空,则返回空字符串数组
     * @param string 字符串
     * @return 割字符串数组
     */
    public static String[] splitByAnyBlank(final String string) {
        if(StringUtil.isEmpty(string)) {
            return new String[0];
        }

        final String pattern = "\\s+";
        return string.split(pattern);
    }

    /**
     * 两边添加
     * @param original 原始信息
     * @param padStr 添加字符串
     * @return 添加后的信息
     */
    public static String bothPad(final String original, final String padStr) {
        return padStr+original+padStr;
    }
}
