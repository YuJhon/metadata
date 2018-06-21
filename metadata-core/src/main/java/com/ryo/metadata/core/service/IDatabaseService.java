package com.ryo.metadata.core.service;

import com.ryo.metadata.core.domain.JdbcVo;

/**
 * 数据库服务层
 *
 * @author bbhou
 * @date 2017/7/31
 */
public interface IDatabaseService {

    void executeMysql(JdbcVo jdbcVo);

    void executeOracle(JdbcVo jdbcVo);

    void executeSqlServer(JdbcVo jdbcVo);

}
