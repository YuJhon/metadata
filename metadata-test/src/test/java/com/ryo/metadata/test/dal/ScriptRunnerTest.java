package com.ryo.metadata.test.dal;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bbhou on 2017/10/25.
 */
public class ScriptRunnerTest {

    @Test
    public void sqlServerTest() throws SQLException, IOException {
        final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=test";
        final String username = "sa";
        final String password = "hydb001*";
        Connection conn = DriverManager.getConnection(url, username, password);
        ScriptRunner runner = new ScriptRunner(conn);
        Resources.setCharset(Charset.forName("UTF-8")); //设置字符集,不然中文乱码插入错误
        runner.setLogWriter(null);//设置是否输出日志

        final File file  = new File("D:\\CODE\\metadata\\metadata-core\\src\\main\\resources\\coreSqlServer.sql");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        runner.runScript(isr);
//        runner.runScript(Resources.getResourceAsReader("sql/CC21-01.sql"));
        runner.closeConnection();
        conn.close();
    }


    @Test
    public void mySqlTest() {

    }


}
