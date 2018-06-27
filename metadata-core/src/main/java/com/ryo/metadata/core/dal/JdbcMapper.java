package com.ryo.metadata.core.dal;

import com.ryo.metadata.core.domain.MetaField;
import com.ryo.metadata.core.domain.MetaModel;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC 访问层
 * @author bbhou
 * @date 2017/8/1
 */
public interface JdbcMapper {

    /**
     * 获取列的信息
     * 1. 对关键字做特殊处理
     * @param column 列名称
     * @return 处理完的列信息
     */
    String getColumn(final String column);

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
     *
     * @param stringList 入参列表
     * @throws SQLException java 异常
     */
    void executeBatchTx(List<String> stringList) throws SQLException;


    /**
     * 数据库元数据信息
     * @return
     */
    DatabaseMetaData metaData() throws SQLException;

    /**
     * 查询所有得标信息
     * @return
     */
    List<MetaModel> selectAllTables();

    /**
     * 查询当前表的所有字段信息
     * @param tableName 表名称
     * @return
     */
    List<MetaField> selectAllFields(String tableName);

    /**
     * 创建元数据表
     */
    void createMetadataTables();

}
