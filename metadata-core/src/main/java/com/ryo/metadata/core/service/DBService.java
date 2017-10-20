package com.ryo.metadata.core.service;

import com.ryo.metadata.core.util.singleton.Singleton;

import java.sql.SQLException;

/**
 * 数据库服务层
 * Created by bbhou on 2017/7/31.
 */
public interface DBService extends Singleton {

    /**
     * 初始化原始数据表
     * 1. 首先判断是否已经创建过对应的元数据表，如果存在则跳过
     * 2. 如果不存在则进行创建
     */
    void initMetadataTables() throws Exception;

    /**
     * 创建实体数据
     */
    void createMetaModelData() throws IllegalAccessException, SQLException;

    /**
     * 创建实体字段数据
     */
    void createMetaFieldData() throws IllegalAccessException, SQLException;

    /**
     * 执行
     */
    void execute() throws Exception;

}
