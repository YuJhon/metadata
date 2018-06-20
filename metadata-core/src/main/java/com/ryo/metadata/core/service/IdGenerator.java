package com.ryo.metadata.core.service;

import com.ryo.metadata.core.util.singleton.Singleton;

/**
 *
 * @author bbhou
 * @date 2017/8/2
 */
public interface IdGenerator extends Singleton {

    /**
     * 生成ID
     * @return 标识字符串
     */
    String genId();

}
