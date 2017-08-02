package com.ryo.metadata.core.dal.impl;

/**
 * [JDBC获取数据库各种信息](http://lavasoft.blog.51cto.com/62575/90739/)
 * SQL Server 数据库访问层
 * <p>
 * 查询数据库中所有表名
 * select table_name from information_schema.tables where table_schema='csdb' and table_type='base table';
 * <p>
 * 查询指定数据库中指定表的所有字段名column_name
 * select column_name from information_schema.columns where table_schema='csdb' and table_name='users'
 *
 * @author bbhou
 */
public class MySqlDBMapper extends AbstractDBMapper {

    @Override
    protected String selectAllTablesSql() {
        String dbName = getDatabaseName();
        String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE TABLE_SCHEMA='"+dbName+"';";
        return sql;
    }

    @Override
    protected String selectAllFieldsSql(String tableName) {
        String dbName = getDatabaseName();
        String sql = "SELECT TABLE_SCHEMA, COLUMN_NAME, IS_NULLABLE, DATA_TYPE, COLUMN_COMMENT " +
                "FROM information_schema.columns where TABLE_NAME='"+tableName+"' AND TABLE_SCHEMA='"+dbName+"';";
        return sql;
    }

}
