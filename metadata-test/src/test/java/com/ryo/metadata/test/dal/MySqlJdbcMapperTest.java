package com.ryo.metadata.test.dal;

import com.ryo.medata.util.util.FileUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by bbhou on 2017/8/2.
 */
public class MySqlJdbcMapperTest {

    @Test
    public void executeTest() throws Exception {
        List<String> stringList = FileUtil.loadSql("D:\\CODE\\metadata\\metadata-test\\src\\main\\resources\\sql\\mysql\\init.sql");

//        JdbcMapper jdbcMapper = new MySqlJdbcMapper();
//        jdbcMapper.executeBatchTx(stringList);

    }

}
