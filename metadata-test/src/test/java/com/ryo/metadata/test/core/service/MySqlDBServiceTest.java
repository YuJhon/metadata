package com.ryo.metadata.test.core.service;

import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.service.impl.MySqlDBService;
import org.junit.Test;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBServiceTest {

    @Test
    public void createMetaModelDataTest() {
        DBService dbService = new MySqlDBService();
        dbService.createMetaModelData();
    }

    @Test
    public void createMetaFieldDataTest() {
        DBService dbService = new MySqlDBService();
        dbService.createMetaFieldData();
    }

//    D:\CODE\metadata\metadata-test\src\main\resources\jdbc_mysql.properties

}
