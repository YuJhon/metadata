package com.ryo.metadata.core.dal.impl;

import com.ryo.medata.util.util.StringUtil;
import com.ryo.metadata.core.constant.DriverNameConstant;
import com.ryo.metadata.core.constant.PathConstant;
import com.ryo.metadata.core.domain.JdbcVo;
import com.ryo.metadata.core.util.KeywordUtil;


/**
 * MySQL 实现
 * @author bbhou
 * @date 2017/8/1
 */
public class MySqlJdbcMapper extends AbstractJdbcMapper {

    public MySqlJdbcMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    public String getColumn(String column) {
        if(KeywordUtil.isMysqlKeyword(column)) {
            return StringUtil.bothPad(column, "`");
        }
        return column;
    }

    @Override
    protected String getScriptPath() {
        return PathConstant.Sql.MYSQL;
    }

    @Override
    protected String driverName() {
        return DriverNameConstant.MYSQL;
    }

    @Override
    protected String selectAllTablesSql() {
        String dbName = getDatabaseName();
        return "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE TABLE_SCHEMA='"+dbName+"';";
    }

    @Override
    protected String selectAllFieldsSql(String tableName) {
        String dbName = getDatabaseName();
        return "SELECT TABLE_SCHEMA, COLUMN_NAME, IS_NULLABLE, DATA_TYPE, COLUMN_COMMENT " +
                "FROM information_schema.columns where TABLE_NAME='"+tableName+"' AND TABLE_SCHEMA='"+dbName+"';";
    }


}
