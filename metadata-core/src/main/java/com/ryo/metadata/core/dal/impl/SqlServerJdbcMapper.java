package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.util.factory.JdbcFactory;
import com.ryo.metadata.core.util.vo.JdbcVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bbhou on 2017/8/1.
 */
public class SqlServerJdbcMapper extends AbstractJdbcMapper {
    @Override
    protected Connection getConnection() {
        try {
            JdbcVo jdbcVo = JdbcFactory.getSqlServer();
            Class.forName(jdbcVo.getDriverClassName());
            Connection connection = DriverManager.getConnection(jdbcVo.getUrl(),
                    jdbcVo.getUsername(),
                    jdbcVo.getPassword());
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
