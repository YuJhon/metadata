package com.ryo.medata.util.util;



/**
 * 参数工具类
 * @author bbhou
 * @date 2017/7/6
 */
public class ArgUtil {

    /**
     * 断言不为空
     * @param object 待验证的对象
     * @param name 对象的名称
     */
    public static void notNull(Object object, String name) throws IllegalArgumentException {
        if(null == object) {
            throw new IllegalArgumentException(name + " can not be null!");
        }
    }

    /**
     * 不可为空
     * @param object 对象
     * @param name 对象名称
     * @param errMsg 错误描述
     */
    public static void notNull(Object object, String name, String errMsg) {
        if(null == object) {
            String errorInfo = String.format("%s %s", name, errMsg);
            throw new IllegalArgumentException(errorInfo);
        }
    }

    /**
     * 校验字符串非空
     * @param string 待检查的字符串
     * @param name 字符串的名称
     * @throws IllegalArgumentException 非法入参
     */
    public static void notEmpty(String string, String name) throws IllegalArgumentException {
        if(StringUtil.isEmpty(string)) {
            throw new IllegalArgumentException(name + " can not be null!");
        }
    }

}
