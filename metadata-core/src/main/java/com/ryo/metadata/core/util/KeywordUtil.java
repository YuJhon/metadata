/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.util;

import com.ryo.medata.util.util.StringUtil;
import com.ryo.metadata.core.constant.AppConstant;
import com.ryo.metadata.core.constant.PathConstant;
import com.ryo.metadata.core.domain.Keyword;
import com.ryo.metadata.core.exception.MetadataRuntimeException;

import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * 彻底搞清楚 web 项目中路径问题。
 *
 * @author bbhou
 * @date 2017/9/26
 */
public final class KeywordUtil {

    private static Set<Keyword> mysql;
    private static Set<Keyword> oracle;
    private static Set<Keyword> sqlServer;

    static {
        synchronized (KeywordUtil.class) {
            mysql = buildKeywordSet(PathConstant.Keyword.MYSQL);
            oracle = buildKeywordSet(PathConstant.Keyword.ORACLE);
            sqlServer = buildKeywordSet(PathConstant.Keyword.SQL_SERVER);
        }
    }

    /**
     * 是否为 mysql 关键字
     * @param name 名称
     * @return {@code true} 是
     */
    public static boolean isMysqlKeyword(final String name) {
        return isKeyword(name, mysql);
    }

    /**
     * 是否为 oracle 关键字
     * @param name 名称
     * @return {@code true} 是
     */
    public static boolean isOracleKeyword(final String name) {
        return isKeyword(name, oracle);
    }

    /**
     * 是否为 sql server 关键字
     * @param name 名称
     * @return {@code true} 是
     */
    public static boolean isSqlServerKeyword(final String name) {
        return isKeyword(name, sqlServer);
    }

    /**
     * 是否为关键字
     * @param name 名称
     * @param keywords 关键字集合
     * @return {@code true} 是
     */
    private static boolean isKeyword(final String name, Set<Keyword> keywords) {
        if(CollectionUtils.isEmpty(keywords)) {
            return false;
        }

        for(Keyword keyword : keywords) {
            if(keyword.getKeyword().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 构建数据集合
     * @param path 文件路径
     * @return 返回数据集合
     */
    private static Set<Keyword> buildKeywordSet(final String path) {
        try {
            Set<Keyword> keywords = new HashSet<>();
            InputStream is = FilePathUtil.getInputStream(path);
            BufferedReader e = new BufferedReader(new InputStreamReader(is,
                    Charset.forName(AppConstant.DEFAULT_CHARSET)));

            while (e.ready()) {
                String entry = e.readLine();
                if (StringUtil.isEmpty(entry)) {
                    continue;
                }
                String[] strings = StringUtil.splitByAnyBlank(entry);
                Keyword keyword = new Keyword();
                keyword.setKeyword(strings[0]);
                keywords.add(keyword);
            }
            return keywords;
        } catch (IOException e) {
            throw new MetadataRuntimeException("Dict 数据加载失败!", e);
        }
    }


}
