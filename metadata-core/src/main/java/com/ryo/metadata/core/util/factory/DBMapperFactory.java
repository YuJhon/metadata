package com.ryo.metadata.core.util.factory;

import com.ryo.metadata.core.dal.DBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by bbhou on 2017/8/2.
 */
public class DBMapperFactory {

    private static final Logger LOGGER = LogManager.getLogger(DBMapperFactory.class);

    private static DBMapper mysql;

    private static DBMapper sqlServer;


}
