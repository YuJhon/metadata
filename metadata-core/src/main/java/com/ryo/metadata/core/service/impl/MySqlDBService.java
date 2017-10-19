package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.MySqlDBMapper;
import com.ryo.metadata.core.dal.impl.MySqlJdbcMapper;
import com.ryo.metadata.core.util.SqlPathUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBService extends AbstractDBService {

    public MySqlDBService(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected DBMapper getDbMapper() {
        return new MySqlDBMapper(jdbcVo);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return new MySqlJdbcMapper(jdbcVo);
    }

    @Override
    protected String getSqlFilePath() {
        return SqlPathUtil.getMysqlPath();
    }


    public static void main(String[] args) {
        System.out.println(SqlPathUtil.getMysqlPath());
    }
}
