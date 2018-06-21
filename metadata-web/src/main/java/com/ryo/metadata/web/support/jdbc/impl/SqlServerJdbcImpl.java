/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.web.support.jdbc.impl;

import com.ryo.metadata.core.constant.DriverNameConstant;
import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.service.impl.SqlServerDBService;
import com.ryo.metadata.core.util.vo.JdbcVo;
import com.ryo.metadata.web.support.jdbc.IJdbc;
import com.ryo.metadata.web.support.jdbc.JdbcContainer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.ryo.metadata.web.support.jdbc.impl.SqlServerJdbcImpl.DATABASE;

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
public class SqlServerJdbcImpl implements IJdbc {

    static final String DATABASE = "sql-server";

    @Override
    public void execute(String host, String port, String database, String username, String password) throws Exception {
        JdbcVo jdbcVo = buildSqlServerVo(host, port, database, username, password);
        DBService dbService = new SqlServerDBService(jdbcVo);
        dbService.execute();
    }

    /**
     * 构建SQL Server 连接池信息
     *
     * @param host     服务地址
     * @param port     端口号
     * @param database 数据库
     * @param username 用户名称
     * @param password 密码
     * @return jdbcVo
     */
    private JdbcVo buildSqlServerVo(final String host, final String port,
                                    final String database, final String username, final String password) {
        JdbcVo jdbcVo = new JdbcVo();
        final String hostActual = StringUtils.defaultIfEmpty(host, "localhost");
        final String portActual = StringUtils.defaultIfEmpty(port, "1433");
        final String usernameActual = StringUtils.defaultIfEmpty(username, "sa");
        final String passwordActual = StringUtils.defaultIfEmpty(password, "123456");

        jdbcVo.setDriverClassName(DriverNameConstant.SQL_SERVER);
        jdbcVo.setUrl(String.format("jdbc:sqlserver://%s:%s;DatabaseName=%s",
                hostActual, portActual, database));
        jdbcVo.setUsername(usernameActual);
        jdbcVo.setPassword(passwordActual);
        return jdbcVo;
    }


//    jdbc.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
//    jdbc.url=jdbc:sqlserver://localhost:1433;DatabaseName=doc
//    jdbc.username=sa
//    jdbc.password=123456

}
