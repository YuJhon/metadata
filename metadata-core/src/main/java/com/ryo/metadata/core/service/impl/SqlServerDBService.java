package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.SqlServerDBMapper;
import com.ryo.metadata.core.dal.impl.SqlServerJdbcMapper;
import com.ryo.metadata.core.util.CoreSqlPathUtil;
import com.ryo.metadata.core.util.SingletonUtil;
import com.ryo.metadata.core.util.SqlExecUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by bbhou on 2017/8/1.
 */
public class SqlServerDBService extends AbstractDBService {

    private static final Logger LOGGER = LogManager.getLogger(SqlServerDBService.class);

    public SqlServerDBService(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected DBMapper getDbMapper() {
        return new SqlServerDBMapper(jdbcVo);
//        return SingletonUtil.getSingleInstance(SqlServerDBMapper.class);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return new SqlServerJdbcMapper(jdbcVo);
//        return SingletonUtil.getSingleInstance(SqlServerJdbcMapper.class);
    }

    @Override
    protected void createMetadataTables() throws Exception {
        LOGGER.info("============================== createMetadataTables START");
        SqlExecUtil.execute(this.jdbcVo, CoreSqlPathUtil.getSqlServerInputStream());
        LOGGER.info("============================== createMetadataTables END");
    }

}
