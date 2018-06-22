package com.ryo.metadata.core.service;

import com.ryo.metadata.core.domain.JdbcVo;

/**
 * 数据库服务层
 *
 * @author bbhou
 * @date 2017/7/31
 */
public interface IDatabaseService {

    /**
     * 执行 mysql
     * @param jdbcVo jdbc 配置
     */
    void executeMysql(JdbcVo jdbcVo);

    /**
     * 执行 oracle
     * @param jdbcVo jdbc 配置
     */
    void executeOracle(JdbcVo jdbcVo);

    /**
     * 执行 sql server
     * @param jdbcVo jdbc 配置
     */
    void executeSqlServer(JdbcVo jdbcVo);

}
