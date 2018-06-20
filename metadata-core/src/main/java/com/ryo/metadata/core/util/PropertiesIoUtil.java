package com.ryo.metadata.core.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * properties 文件工具类
 * @author houbinbin
 * @date 16/9/11
 * - Properties 文件读写工具
 * - 避免和 spring 属性工具类混淆
 */
public class PropertiesIoUtil {

    private PropertiesIoUtil() {
    }

    private static Properties prop = new Properties();

    /**
     * 根路径
     */
    private static final String ROOT_PATH = "/";

    /**
     * 加载配置文件属性
     *
     * @param filePath 文件路径
     */
    public static void loadProperties(String filePath) {
        try (InputStream in = new FileInputStream(filePath);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            prop.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取对应的value
     *
     * @param key key
     * @return 对应的值
     */
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

}
