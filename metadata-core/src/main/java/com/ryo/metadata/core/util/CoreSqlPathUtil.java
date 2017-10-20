package com.ryo.metadata.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * 彻底搞清楚 web 项目中路径问题。
 * Created by bbhou on 2017/9/26.
 */
public class CoreSqlPathUtil {

    /**
     * 文件路径
     */
    private static final String FILE_PATH = "coreMysql.sql";


    /**
     * MySQL 路径
     */
    private static String mysql_path = "";

    static {


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
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }

        return inputStream;
    }


    /**
     *
     * @return
     */
    public static String getMysqlPath() {
        return mysql_path;
    }

//    public static void main(String[] args) {
//        File file = new InputStream().;
//    }
}
