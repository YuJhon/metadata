package com.ryo.metadata.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 彻底搞清楚 web 项目中路径问题。
 *
 * @author bbhou
 * @date 2017/9/26
 */
public class CoreSqlPathUtil {

    private static final Logger LOGGER = LogManager.getLogger(CoreSqlPathUtil.class);

    /**
     * MySQL 文件路径
     */
    private static final String MYSQL_PATH = "coreMysql.sql";

    /**
     * SQL-SERVER 文件路径
     */
    private static final String SQL_SERVER_PATH = "coreSqlServer.sql";

    /**
     * ORACLE 文件路径
     */
    private static final String ORACLE_PATH = "coreOracle.sql";

    /**
     * 获取文件对应输入流
     *
     * @param filePath 文件路径
     * @return
     */
    private static InputStream getInputStream(final String filePath) throws Exception {
        InputStream inputStream = null;

        try {
            inputStream = new URL(filePath).openStream();
        } catch (MalformedURLException localMalformedURLException) {
            try {
                inputStream = new FileInputStream(filePath);
            } catch (Exception localException2) {
                ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
                if (localClassLoader == null) {
                    localClassLoader = CoreSqlPathUtil.class.getClassLoader();
                }
                inputStream = localClassLoader.getResourceAsStream(filePath);
                if (inputStream == null) {
                    throw new Exception("Could not find file: " + filePath);
                }
            }
        } catch (IOException localIOException1) {
            throw new Exception(localIOException1);
        }

        return inputStream;
    }


    public static InputStream getMysqlInputStream() throws Exception {
        return getInputStream(MYSQL_PATH);
    }

    public static InputStream getSqlServerInputStream() throws Exception {
        return getInputStream(SQL_SERVER_PATH);
    }

    public static InputStream getOracleInputStream() throws Exception {
        return getInputStream(ORACLE_PATH);
    }

}
