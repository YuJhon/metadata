package com.ryo.metadata.core.service;

/**
 * 数据库服务层
 * Created by bbhou on 2017/7/31.
 */
public interface DBService {

    /**
     * 创建实体数据
     */
    void createMetaModelData();

    /**
     * 创建实体字段数据
     */
    void createMetaFieldData();

}
