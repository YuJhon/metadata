package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.SqlServerDBMapper;
import com.ryo.metadata.core.dal.impl.SqlServerJdbcMapper;

/**
 * Created by bbhou on 2017/8/1.
 */
public class SqlServerDBService extends AbstractDBService {

    private static DBMapper dbMapper = null;

    private static JdbcMapper jdbcMapper = null;

    @Override
    protected DBMapper getDbMapper() {
        if(null == dbMapper) {
            dbMapper = new SqlServerDBMapper();
        }
        return dbMapper;
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        if(null == jdbcMapper) {
            jdbcMapper = new SqlServerJdbcMapper();
        }
        return jdbcMapper;
    }

}
