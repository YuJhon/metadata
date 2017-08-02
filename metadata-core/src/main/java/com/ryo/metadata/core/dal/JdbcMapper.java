package com.ryo.metadata.core.dal;

import com.ryo.metadata.core.util.singleton.Singleton;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bbhou on 2017/8/1.
 */
public interface JdbcMapper extends Singleton {

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

    /**
     * 执行SQL
     * 1.包含事物处理
     * 2. sql 本身包含多条sql。同时成功或者失败。
     */
    void executeTransaction(List<String> stringList) throws SQLException;


    /**
     * 数据库元数据信息
     * @return
     */
    DatabaseMetaData metaData() throws SQLException;

}
