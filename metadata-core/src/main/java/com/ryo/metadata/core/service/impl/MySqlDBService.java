package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.MySqlDBMapper;
import com.ryo.metadata.core.dal.impl.MySqlJdbcMapper;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBService extends AbstractDBService {

    private static final Logger LOGGER = LogManager.getLogger(MySqlDBService.class);

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

//    @Override
//    protected boolean hasInitMetadataTables() {
//        return false;
//    }

    @Override
    protected void createMetadataTables() {
        LOGGER.info("============================== createMetadataTables START");
        LOGGER.info("============================== createMetadataTables END");
    }

}
