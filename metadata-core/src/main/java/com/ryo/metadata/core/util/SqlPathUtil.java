package com.ryo.metadata.core.util;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by bbhou on 2017/9/26.
 */
public class SqlPathUtil {

    private static String MYSQL_PATH = "";

    private static String SQL_SERVER_PATH = "";

    static {

        try {
            URL mysqlUrl = SqlPathUtil.class.getResource("/sql/mysql.sql");
            URL sqlServerUrl = SqlPathUtil.class.getResource("/sql/sqlServer.sql");
            MYSQL_PATH = mysqlUrl.toURI().getPath();
            SQL_SERVER_PATH = sqlServerUrl.toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace(); //实际请使用 LOG 代替
        }

    }

    public static String getMysqlPath() {
        return MYSQL_PATH;
    }

    public static String getSqlServerPath() {
        return SQL_SERVER_PATH;
    }


    public static void main(String[] args) {
        System.out.println(getMysqlPath());
        System.out.println(getSqlServerPath());
    }
}
