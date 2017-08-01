package com.ryo.medata.util.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by bbhou on 2017/8/1.
 */
public class DBUtil {

    private static final Logger LOGGER = LogManager.getLogger(DBUtil.class);

    /**
     * 获取连接的数据库名称
     *
     * Connection connection = JDBC_MAPPER.metaData().getConnection();
     * String dbName = connection.getCatalog();
     *
     * @param url       数据库连接的URL
     * @param userNames oracle 数据库才需要传入此参数。
     * @return
     */
    public static String getConnectionDatabaseName(String url, String... userNames) {
        if (url.toLowerCase().contains("jdbc:oracle:"))//oracle.jdbc.driver.OracleDriver
        {
            return userNames[0].toUpperCase().trim();
        } else if (url.toLowerCase().contains("jdbc:db2:"))//com.ibm.db2.jdbc.app.DB2Driver
        {
            return url.substring(url.lastIndexOf("/") + 1).trim();
        } else if (url.toLowerCase().contains("jdbc:microsoft:"))//com.microsoft.jdbc.sqlserver.SQLServerDriver
        {
            return url.substring(url.lastIndexOf("=") + 1).trim();
        } else if (url.toLowerCase().contains("jdbc:sybase:"))//com.sybase.jdbc.SybDriver
        {
            return url.substring(url.lastIndexOf("/") + 1).trim();
        } else if (url.toLowerCase().contains("jdbc:informix-sqli:"))//com.informix.jdbc.IfxDriver
        {
            return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(":")).trim();
        } else if (url.toLowerCase().contains("jdbc:mysql:"))//org.gjt.mm.mysql.Driver
        {
            return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?")).trim();
        } else if (url.toLowerCase().contains("jdbc:postgresql:"))//org.postgresql.Driver
        {
            return url.substring(url.lastIndexOf("/") + 1).trim();
        } else if (url.toLowerCase().contains("*.mdb"))//sun.jdbc.odbc.JdbcOdbcDriver
        {
            return url.substring(url.lastIndexOf("=") + 1).trim();
        } else {
            throw new UnsupportedOperationException("url: " + url + " are not support!");
        }
    }

    /**
     * 打印查询结果信息
     * @param rs
     * @throws SQLException
     */
    public void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i + 1) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.println();
        }
        rs.close();
    }

}
