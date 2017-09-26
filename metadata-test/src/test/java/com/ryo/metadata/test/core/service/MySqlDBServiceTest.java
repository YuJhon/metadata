package com.ryo.metadata.test.core.service;

import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.service.impl.MySqlDBService;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBServiceTest {

    @Test
    public void createMetaModelDataTest() throws SQLException, IllegalAccessException {
        DBService dbService = new MySqlDBService();
        dbService.createMetaModelData();
    }

    @Test
    public void createMetaFieldDataTest() throws SQLException, IllegalAccessException {
        DBService dbService = new MySqlDBService();
        dbService.createMetaFieldData();
    }

    @Test
    public void initMetaDataTablesTest() throws Exception {
        DBService dbService = new MySqlDBService();
        dbService.initMetadataTables();
    }

}
