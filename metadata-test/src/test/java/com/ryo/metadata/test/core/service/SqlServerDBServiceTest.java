package com.ryo.metadata.test.core.service;

import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.service.impl.MySqlDBService;
import com.ryo.metadata.core.service.impl.SqlServerDBService;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by bbhou on 2017/8/1.
 */
public class SqlServerDBServiceTest {

    @Test
    public void createMetaModelDataTest() throws SQLException, IllegalAccessException {
        DBService dbService = new SqlServerDBService();
        dbService.createMetaModelData();
    }

    @Test
    public void createMetaFieldDataTest() throws SQLException, IllegalAccessException {
        DBService dbService = new SqlServerDBService();
        dbService.createMetaFieldData();
    }

}
