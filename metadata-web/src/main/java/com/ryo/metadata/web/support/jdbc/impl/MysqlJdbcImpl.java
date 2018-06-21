/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.web.support.jdbc.impl;

import com.ryo.metadata.core.constant.DriverNameConstant;
import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.service.impl.MySqlDBService;
import com.ryo.metadata.core.util.vo.JdbcVo;
import com.ryo.metadata.web.support.jdbc.IJdbc;
import com.ryo.metadata.web.support.jdbc.JdbcContainer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.ryo.metadata.web.support.jdbc.impl.MysqlJdbcImpl.DATABASE;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/20 下午4:09  </pre>
 * <pre> Project: metadata  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Service(DATABASE + JdbcContainer.SERVICE_NAME)
public class MysqlJdbcImpl implements IJdbc {

    static final String DATABASE = "mysql";

    @Override
    public void execute(String host, String port, String database, String username, String password) throws Exception {
        JdbcVo jdbcVo = buildMySqlJdbcVo(host, port, database, username, password);
        DBService dbService = MySqlDBService.getInstance(jdbcVo);
        dbService.execute();
    }

    /**
     * Mysql 的链接信息
     * @param port     端口号
     * @param database 数据库
     * @param username 用户名称
     * @param password 密码
     * @return 链接信息
     */
    private JdbcVo buildMySqlJdbcVo(final String host, final String port,
                                    final String database, final String username, final String password) {
        final String hostActual = StringUtils.defaultIfEmpty(host, "127.0.0.1");
        final String portActual = StringUtils.defaultIfEmpty(port, "3306");
        final String usernameActual = StringUtils.defaultIfEmpty(username, "root");
        final String passwordActual = StringUtils.defaultIfEmpty(password, "123456");

        JdbcVo jdbcVo = new JdbcVo();
        jdbcVo.setDriverClassName(DriverNameConstant.MYSQL);
        jdbcVo.setUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&useOldAlias",
                hostActual, portActual, database));
        jdbcVo.setUsername(usernameActual);
        jdbcVo.setPassword(passwordActual);
        return jdbcVo;
    }

//    jdbc.driverClassName=com.mysql.jdbc.Driver
//    jdbc.url=jdbc:mysql://127.0.0.1:3306/fate?useUnicode=true&characterEncoding=UTF-8&useOldAlias
//    jdbc.username=root
//    jdbc.password=123456789


}
