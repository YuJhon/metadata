package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.dal.JdbcMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by bbhou on 2017/8/1.
 */
public abstract class AbstractJdbcMapper implements JdbcMapper {

    /**
     * 获取数据库连接
     * @return
     */
    protected abstract Connection getConnection();

    @Override
    public ResultSet query(String querySql) {
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querySql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void execute(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

}
