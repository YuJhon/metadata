/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.web.support.jdbc.impl;

import com.ryo.metadata.core.constant.DriverNameConstant;
import com.ryo.metadata.core.domain.JdbcVo;
import com.ryo.metadata.core.service.IDatabaseService;
import com.ryo.metadata.web.support.jdbc.IJdbc;
import com.ryo.metadata.web.support.jdbc.JdbcContainer;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ryo.metadata.web.support.jdbc.impl.OracleJdbcImpl.DATABASE;

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
public class OracleJdbcImpl implements IJdbc {

    /**
     * OracleJdbcImpl's Logger
     */
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(OracleJdbcImpl.class);

    static final String DATABASE = "oracle";

    @Autowired
    private IDatabaseService databaseService;

    @Override
    public void execute(String host, String port, String database, String username, String password)
            throws Exception {
        JdbcVo jdbcVo = buildOracleJdbcVo(host, port, database, username, password);
        databaseService.executeOracle(jdbcVo);
    }

    /**
     * Oracle 的链接信息
     * PS: oracle 是没有用户的概念的
     * @param port     端口号
     * @param database 数据库信息 类似于 service 此处填写 XE/orcl 等
     * @param username 用户名称
     * @param password 密码
     * @return 链接信息
     */
    private JdbcVo buildOracleJdbcVo(final String host, final String port,
                                     final String database,
                                     final String username, final String password) {
        final String hostActual = StringUtils.defaultIfEmpty(host, "127.0.0.1");
        final String portActual = StringUtils.defaultIfEmpty(port, "49161");
        final String serviceActual = StringUtils.defaultIfEmpty(database, "XE");
        final String usernameActual = StringUtils.defaultIfEmpty(username, "system");
        final String passwordActual = StringUtils.defaultIfEmpty(password, "123456");

        JdbcVo jdbcVo = new JdbcVo();
        jdbcVo.setDriverClassName(DriverNameConstant.ORACLE);
        jdbcVo.setUrl(String.format("jdbc:oracle:thin:@%s:%s:%s",
                hostActual, portActual, serviceActual));
        jdbcVo.setUsername(usernameActual);
        jdbcVo.setPassword(passwordActual);

        LOGGER.info("JDBC:{}", jdbcVo);
        return jdbcVo;
    }

//    jdbc.driverClassName=oracle.jdbc.OracleDriver
//    jdbc.url=jdbc:oracle:thin:@127.0.0.1:49161:XE
//    jdbc.username=system
//    jdbc.password=123456

}
