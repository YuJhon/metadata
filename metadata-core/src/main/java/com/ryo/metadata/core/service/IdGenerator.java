package com.ryo.metadata.core.service;

import com.ryo.metadata.core.util.singleton.Singleton;

/**
 * Created by bbhou on 2017/8/2.
 */
public interface IdGenerator extends Singleton {

    /**
     * 生成ID
     * @return
     */
    String genId();

}
