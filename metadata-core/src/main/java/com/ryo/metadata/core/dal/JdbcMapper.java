package com.ryo.metadata.core.dal;

import com.ryo.metadata.core.util.singleton.Singleton;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC 访问层
 * @author bbhou
 * @date 2017/8/1
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
    void execute(String sql);

    /**
     * 批量执行脚本；不包含事物。
     * @param stringList
     */
    void executeBatch(List<String> stringList);

    /**
     * 执行SQL
     * 1.包含事物处理
     * 2. sql 本身包含多条sql。同时成功或者失败。
     * @param stringList 入参列表
     */
    void executeBatchTx(List<String> stringList) throws SQLException;


    /**
     * 数据库元数据信息
     * @throws sql exception
     * @return
     */
    DatabaseMetaData metaData() throws SQLException;

}
