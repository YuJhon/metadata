/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.constant.DriverNameConstant;
import com.ryo.metadata.core.constant.PathConstant;
import com.ryo.metadata.core.domain.JdbcVo;

/**
 * oracle jdbcMapper
 *
 * @author bbhou
 * @date 2017/8/1
 */
public class OracleJdbcMapper extends AbstractJdbcMapper {

    public OracleJdbcMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected String getScriptPath() {
        return PathConstant.Sql.ORACLE;
    }

    @Override
    protected String driverName() {
        return DriverNameConstant.ORACLE;
    }

    @Override
    protected String selectAllTablesSql() {
        return "select a.TABLE_NAME,b.COMMENTS from user_tables a,user_tab_comments b " +
                "WHERE a.TABLE_NAME=b.TABLE_NAME order by TABLE_NAME";
    }

    @Override
    protected String selectAllFieldsSql(String tableName) {
        final String database = super.getDatabaseName();
        return "SELECT '" + database + "', col.column_name, col.nullable, col.data_type, comment.comments" +
                "FROM user_tab_columns col LEFT JOIN all_col_comments comment " +
                "ON col.column_name=comment.column_name" +
                "WHERE table_name='" + tableName + "'";
    }

}
