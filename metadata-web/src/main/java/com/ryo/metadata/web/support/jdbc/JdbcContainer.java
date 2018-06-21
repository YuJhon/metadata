/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.web.support.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/20 下午4:07  </pre>
 * <pre> Project: metadata  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class JdbcContainer {

    public static final String SERVICE_NAME = "JdbcService";

    private final Map<String, IJdbc> jdbcServciceMap;

    @Autowired
    public JdbcContainer(Map<String, IJdbc> jdbcServciceMap) {
        this.jdbcServciceMap = jdbcServciceMap;
    }

    /**
     * 获取对应的服务
     * @param database 数据库类型
     * @return 对应的服务
     */
    public IJdbc getSerivce(String database) {
        return jdbcServciceMap.get(database + SERVICE_NAME);
    }

}
