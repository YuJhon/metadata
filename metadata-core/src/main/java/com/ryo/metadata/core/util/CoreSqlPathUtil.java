package com.ryo.metadata.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * 彻底搞清楚 web 项目中路径问题。
 * Created by bbhou on 2017/9/26.
 */
public class CoreSqlPathUtil {

    private static final Logger LOGGER = LogManager.getLogger(CoreSqlPathUtil.class);

    /**
     * 文件路径
     */
    private static final String MYSQL_PATH = "coreMysql.sql";

    private static final String SQL_SERVER_PATH = "coreSqlServer.sql";


    private static InputStream mysqlInputStream;
    private static InputStream sqlServerInputStream;

    static {
        try {
            mysqlInputStream = getInputStream(MYSQL_PATH);
            sqlServerInputStream = getInputStream(SQL_SERVER_PATH);
        } catch (Exception e) {
            LOGGER.error("Init sql inputStream meet ex: "+e, e);
        }

    }



    /**
     * 获取文件对应输入流
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


    /**
     *
     * @return
     */
    public static InputStream getMysqlInputStream() {
        return mysqlInputStream;
    }

    public static InputStream getSqlServerInputStream() {
        return sqlServerInputStream;
    }

}
