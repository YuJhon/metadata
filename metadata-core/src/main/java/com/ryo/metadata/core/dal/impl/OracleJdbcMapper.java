/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.util.vo.JdbcVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * oracle jdbcMapper
 * @author bbhou
 * @date 2017/8/1
 */
public class OracleJdbcMapper extends AbstractJdbcMapper {

    public OracleJdbcMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected Connection getConnection() {
        try {
            Class.forName(jdbcVo.getDriverClassName());
            return DriverManager.getConnection(jdbcVo.getUrl(),
                    jdbcVo.getUsername(),
                    jdbcVo.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:49161:XE","system","123456");
    }

//    jdbc.driverClassName=oracle.jdbc.OracleDriver
//    jdbc.url=jdbc:oracle:thin:@127.0.0.1:49161:XE
//    jdbc.username=system
//    jdbc.password=123456
}
