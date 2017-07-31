package com.ryo.metadata.core.util;

import java.sql.*;

/**
 * Created by bbhou on 2017/7/31.
 */
public class DalUtil {

//    protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//
//    static {
//        try {
//            /**
//             * 保证被加载到 JVM 中
//             */
//            Class.forName(dbClassName);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connnection;
    }

    public static ResultSet query(String QueryStr) {
        ResultSet set = findForResultSet(QueryStr);
        return set;
    }

    /**
     * 执行SQL脚本
     * @param sql
     * @throws SQLException
     */
    public static void execute(String sql) throws SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    public static ResultSet findForResultSet(String sql) {
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
