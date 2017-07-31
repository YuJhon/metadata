package com.ryo.medata.util.util;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by houbinbin on 16/5/28.
 */
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * 获取文件内容
     *
     * @param filePath 文件路径
     * @return 文件不存在或异常等, 返回空字符串
     */
    public static String getFileContent(String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            return StringUtil.EMPTY;
        }
        File file = new File(filePath);
        if (file.exists()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                return getFileContent(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("文件不存在" + filePath);
                return StringUtil.EMPTY;
            }
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取文件内容
     * 默认编码UTF8
     *
     * @param inputStream 输入流
     * @return 文件内容
     */
    public static String getFileContent(InputStream inputStream) {
        return getFileContent(inputStream, StandardCharsets.UTF_8.toString());
    }

    /**
     * 获取文件内容
     *
     * @param inputStream 文件输入流
     * @param charset     文件编码
     * @return 文件内容字符串
     */
    public static String getFileContent(InputStream inputStream, String charset) {
        try {
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            int readSize = inputStream.read(bytes);
            inputStream.close();
            String jsonText;
            jsonText = new String(bytes, charset);
            return jsonText;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return StringUtil.EMPTY;
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    /**
     * 获取指定路径文件的每一行内容
     *
     * @param filePath
     * @param initLine
     * @return
     */
    public static List<String> getFileContentEachLine(String filePath, int initLine) {
        File file = new File(filePath);
        return getFileContentEachLine(file, initLine);
    }

    public static List<String> getFileContentEachLine(String filePath) {
        File file = new File(filePath);
        return getFileContentEachLine(file, 0);
    }


    /**
     * 获取指定文件的每一行内容。并对内容进行trim()操作。
     *
     * @param filePath
     * @param initLine
     * @return
     */
    public static List<String> getFileContentEachLineTrim(String filePath, int initLine) {
        List<String> stringList = getFileContentEachLine(filePath, initLine);
        List<String> resultList = new LinkedList<>();

        for (String string : stringList) {
            resultList.add(string.trim());
        }

        return resultList;
    }

    /**
     * 获取指定文件的每一行内容
     * 默认初始行数为0
     *
     * @param file
     * @return
     */
    public static List<String> getFileContentEachLine(File file) {
        return getFileContentEachLine(file, 0);
    }

    /**
     * 获取指定文件的每一行内容
     * [TWR](http://blog.csdn.net/doctor_who2004/article/details/50901195)
     *
     * @param file     指定读取文件
     * @param initLine 初始读取行数
     * @return 错误返回空列表
     * @since 1.7
     */
    public static List<String> getFileContentEachLine(File file, int initLine) {
        List<String> contentList = new LinkedList<>();

        if (!file.exists()) {
            System.err.println("文件不存在");
            return contentList;
        }

        String charset = "UTF-8";  //暂时使用此编码
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            int lineNo = 0;// 用于记录行号
            while (lineNo < initLine) {
                lineNo++;
                bufferedReader.readLine();
            }

            String dataEachLine;   //每一行的内容
            while ((dataEachLine = bufferedReader.readLine()) != null) {
                lineNo++;
                //跳过空白行
//                if (StringUtil.isBlank(dataEachLine)) {
//                    continue;
//                }
                contentList.add(dataEachLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ListUtil.EMPTY_LIST;
        }

        return contentList;
    }


    /**
     * 共同的字段
     *
     * @param devPath
     * @param rawPath
     * @return
     */
    public static List<String> sameFields(final String devPath, final String rawPath) {
        List<String> resultList = new LinkedList<>();
        List<String> ignoreFields = Arrays.asList("IID");

        List<String> devList = FileUtil.getFileContentEachLine(devPath);
        List<String> rawList = FileUtil.getFileContentEachLine(rawPath);

        for (String dev : devList) {
            if (rawList.contains(dev)) {
                if (ignoreFields.contains(dev)) {
                    continue;
                }
                resultList.add(dev);
            }
        }

        return resultList;
    }

    /**
     *
     * @param devPath
     * @param rawPath
     * @return
     */
    public static List<String> sameFieldsWithDev(final String devPath, final String rawPath)
    {
        final String DEV = "_dev.";
        List<String> result = new LinkedList<>();
        List<String> originalList = sameFields(devPath, rawPath);

        for(String original : originalList)
        {
            String content = DEV+original;
            result.add(content);
        }
        return result;
    }


}
