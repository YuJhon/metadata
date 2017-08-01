package com.ryo.metadata.core.util;

import java.sql.*;

/**
 * Created by bbhou on 2017/7/31.
 */
public class DalUtil {

    private static class JdbcKey {
        private static final String jdbc_driverClassName = "jdbc.driverClassName";
        private static final String jdbc_url = "jdbc.url";
        private static final String jdbc_username = "jdbc.username";
        private static final String jdbc_password = "jdbc.password";
    }

    public static Connection getConnection() {
        Connection connnection = null;
        try {
            String db_url = "jdbc:sqlserver://localhost:1433;DatabaseName=test";
            String db_username = "sa";
            String db_password = "haiyi";
            connnection = DriverManager.getConnection(db_url,
                    db_username,
                    db_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connnection;
    }

    /**
     * 执行SQL脚本
     *
     * @param sql
     * @throws SQLException
     */
    public static void execute(String sql) throws SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    /**
     * 查询
     * @param sql
     * @return
     */
    public static ResultSet query(String sql) {
        Connection conn = null;
        conn = getConnection();
        ResultSet rs = null;
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
