package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.MySqlDBMapper;
import com.ryo.metadata.core.dal.impl.MySqlJdbcMapper;
import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.util.CoreSqlPathUtil;
import com.ryo.metadata.core.util.MybatisSqlExecUtil;
import com.ryo.metadata.core.util.SqlExecUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author bbhou
 * @date 2017/8/1
 */
public class MySqlDBService extends AbstractDBService {

    private static final Logger LOGGER = LogManager.getLogger(MySqlDBService.class);



    public MySqlDBService(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    /**
     * 获取对应单例信息
     */
    private static DBService service;
    public static DBService getInstance(JdbcVo jdbcVo) {
        if (service == null) {
            synchronized (MySqlDBService.class) {
                service = new MySqlDBService(jdbcVo);
            }
        }
        return service;
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
    protected void createMetadataTables() throws Exception {
        LOGGER.info("============================== createMetadataTables START");
        MybatisSqlExecUtil.execute(this.jdbcVo, CoreSqlPathUtil.getMysqlInputStream());
        LOGGER.info("============================== createMetadataTables END");
    }

}
