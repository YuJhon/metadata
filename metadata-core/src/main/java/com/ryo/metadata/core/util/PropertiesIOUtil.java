package com.ryo.metadata.core.util;

import com.ryo.medata.util.util.DBSqlUtil;
import com.ryo.metadata.core.domain.MetaField;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

/**
 * Created by houbinbin on 16/9/11.
 * - Properties 文件读写工具
 * - 避免和 spring 属性工具类混淆
 */
public class PropertiesIOUtil {

    private PropertiesIOUtil() {
    }

    private static Properties _prop = new Properties();

    /**
     * 根路径
     */
    private static final String ROOT_PATH = "/";

//    /**
//     * 加载配置文件属性
//     *
//     * @param path
//     * @param fileName
//     */
//    public static void loadProperties(String path, String fileName) {
//        String fullFilePath = path+fileName;
//        loadProperties(fullFilePath);
//    }

    /**
     * 加载配置文件属性
     *
     * @param filePath
     */
    public static void loadProperties(String filePath) {
//        try (InputStream in = PropertiesUtil.class.getResourceAsStream(filePath);
        try (InputStream in = new FileInputStream(filePath);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            _prop.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取对应的value
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return _prop.getProperty(key);
    }

    public static void main(String[] args) throws IllegalAccessException {
        String dbObjectName = "dbObjectName";
        String columnName = "columnName";
        String dataType = "string";
        String comment = "comment";

        MetaField metaField = new MetaField();
        metaField.setName(columnName);
        metaField.setDataType(dataType);
        metaField.setNullable(true);
        metaField.setDescription(comment);
        metaField.setDbObjectName(dbObjectName);
        metaField.setCreateTime(new Date());
        metaField.setUpdateTime(new Date());
        String sql = DBSqlUtil.insert("_table", metaField, null);
        System.out.println(sql);
    }

}
