package com.ryo.metadata.core.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bbhou on 2017/8/1.
 */
public interface JdbcMapper {

    /**
     * 执行查询语句
     * @param querySql
     * @return
     */
    ResultSet query(String querySql);

    /**
     * 执行SQL
     * @param sql
     */
    void execute(String sql) throws SQLException;

}
