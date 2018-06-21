/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.OracleDBMapper;
import com.ryo.metadata.core.dal.impl.OracleJdbcMapper;
import com.ryo.metadata.core.dal.impl.SqlServerDBMapper;
import com.ryo.metadata.core.dal.impl.SqlServerJdbcMapper;
import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.util.CoreSqlPathUtil;
import com.ryo.metadata.core.util.MybatisSqlExecUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * oracle 数据库实现
 * @author bbhou
 * @date 2017/8/1
 */
public class OracleDBService extends AbstractDBService {

    private static final Logger LOGGER = LogManager.getLogger(OracleDBService.class);

    public OracleDBService(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    /**
     * 获取对应单例信息
     */
    private static DBService service;
    public static DBService getInstance(JdbcVo jdbcVo) {
        if (service == null) {
            synchronized (MySqlDBService.class) {
                service = new OracleDBService(jdbcVo);
            }
        }
        return service;
    }

    @Override
    protected DBMapper getDbMapper() {
        return new OracleDBMapper(jdbcVo);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return new OracleJdbcMapper(jdbcVo);
    }

    @Override
    protected void createMetadataTables() throws Exception {
        LOGGER.info("============================== createMetadataTables START");
        MybatisSqlExecUtil.execute(this.jdbcVo, CoreSqlPathUtil.getSqlServerInputStream());
        LOGGER.info("============================== createMetadataTables END");
    }

}
