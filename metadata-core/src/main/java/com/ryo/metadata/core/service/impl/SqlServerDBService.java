package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.SqlServerDBMapper;
import com.ryo.metadata.core.dal.impl.SqlServerJdbcMapper;
import com.ryo.metadata.core.util.CoreSqlPathUtil;
import com.ryo.metadata.core.util.MybatisSqlExecUtil;
import com.ryo.metadata.core.util.SingletonUtil;
import com.ryo.metadata.core.util.SqlExecUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author bbhou
 * @date 2017/8/1
 */
public class SqlServerDBService extends AbstractDBService {

    private static final Logger LOGGER = LogManager.getLogger(SqlServerDBService.class);

    public SqlServerDBService(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected DBMapper getDbMapper() {
        return new SqlServerDBMapper(jdbcVo);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return new SqlServerJdbcMapper(jdbcVo);
    }

    @Override
    protected void createMetadataTables() throws Exception {
        LOGGER.info("============================== createMetadataTables START");
        MybatisSqlExecUtil.execute(this.jdbcVo, CoreSqlPathUtil.getSqlServerInputStream());
        LOGGER.info("============================== createMetadataTables END");
    }

}
