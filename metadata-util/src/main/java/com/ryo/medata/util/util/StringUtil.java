package com.ryo.medata.util.util;

/**
 * Created by bbhou on 2017/7/31.
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static boolean isEmpty(final String string) {
        return null == string || EMPTY.equals(string);
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
}
