package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.MySqlDBMapper;
import com.ryo.metadata.core.dal.impl.MySqlJdbcMapper;
import com.ryo.metadata.core.util.SingletonUtil;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBService extends AbstractDBService {

    @Override
    protected DBMapper getDbMapper() {
        return SingletonUtil.getSingleInstance(MySqlDBMapper.class);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return SingletonUtil.getSingleInstance(MySqlJdbcMapper.class);
    }

}
