package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.util.vo.JdbcVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bbhou
 * @date 2017/8/1
 */
public class SqlServerJdbcMapper extends AbstractJdbcMapper {

    public SqlServerJdbcMapper(JdbcVo jdbcVo) {
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
}
