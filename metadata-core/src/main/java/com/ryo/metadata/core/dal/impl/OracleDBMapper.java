/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.util.vo.JdbcVo;

/**
 * SQL Server 数据库访问层
 * [](http://www.cnblogs.com/songxingzhu/p/5849029.html)
 *
 * @author bbhou
 * @date 2017/7/31
 */
public class OracleDBMapper extends AbstractDBMapper {

    public OracleDBMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return new SqlServerJdbcMapper(jdbcVo);
    }

    @Override
    protected String selectAllTablesSql() {
        return "select a.TABLE_NAME,b.COMMENTS from user_tables a,user_tab_comments b " +
                "WHERE a.TABLE_NAME=b.TABLE_NAME order by TABLE_NAME";
    }

    @Override
    protected String selectAllFieldsSql(String tableName) {
        final String database = jdbcVo.getUsername();
        return "SELECT '"+database+"', col.column_name, col.nullable, col.data_type, comment.comments" +
                "FROM user_tab_columns col LEFT JOIN all_col_comments comment " +
                "ON col.column_name=comment.column_name" +
                "WHERE table_name='"+tableName+"'";
    }
}
