package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.SqlServerDBMapper;
import com.ryo.metadata.core.dal.impl.SqlServerJdbcMapper;
import com.ryo.metadata.core.util.SingletonUtil;
import com.ryo.metadata.core.util.SqlPathUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;

/**
 * Created by bbhou on 2017/8/1.
 */
public class SqlServerDBService extends AbstractDBService {

    public SqlServerDBService(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected DBMapper getDbMapper() {
        return SingletonUtil.getSingleInstance(SqlServerDBMapper.class);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return SingletonUtil.getSingleInstance(SqlServerJdbcMapper.class);
    }

    @Override
    protected String getSqlFilePath() {
        return SqlPathUtil.getSqlServerPath();
    }

}
