package com.ryo.metadata.web.util;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by bbhou on 2017/10/20.
 */
public class SqlPathUtil {

    /**
     * 文件路径
     */
    private static final String MYSQL_FILE_PATH = "/mysql.sql";


    /**
     * MySQL 路径
     */
    private static String mysql_path = "";

    static {
        URL url = SqlPathUtil.class.getResource(MYSQL_FILE_PATH);
        try {
            mysql_path = url.toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace(); //实际请使用 LOG 代替
        }
    }

    /**
     *
     * @return
     */
    public static String getMysqlPath() {
        return mysql_path;
    }

}
