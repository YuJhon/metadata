package com.ryo.metadata.core.util;

import com.ryo.metadata.core.util.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例工具方法
 * 需要对象提供空构造器
 * 1. 此方法可以写的更加简单，不需要对象实现任何接口。直接传入就可以调用。
 * @author houbinbin
 * @since 1.7
 * @see Singleton 单例接口
 */
public class SingletonUtil {

    private static final Logger LOGGER = LogManager.getLogger(SingletonUtil.class);

    /**
     * 线程安全
     */
    private static ConcurrentHashMap<Class, Singleton> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 获取单例的唯一实例
     * @param singletonClass 单例类
     * @return
     */
    public static <T extends Singleton> T getSingleInstance(Class<T> singletonClass) {
        Singleton result = concurrentHashMap.get(singletonClass);
        if(null == result) {
            try {
                Singleton singleton = singletonClass.newInstance();
                concurrentHashMap.put(singletonClass, singleton);
                return (T) singleton;
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("getSingleInstance meet ex: "+e, e);
                return null;
            }
        }
        return (T) result;
    }

}
