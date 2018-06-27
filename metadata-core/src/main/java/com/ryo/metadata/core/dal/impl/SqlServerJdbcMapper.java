package com.ryo.metadata.core.dal.impl;

import com.ryo.medata.util.util.StringUtil;
import com.ryo.metadata.core.constant.DriverNameConstant;
import com.ryo.metadata.core.constant.PathConstant;
import com.ryo.metadata.core.domain.JdbcVo;
import com.ryo.metadata.core.util.KeywordUtil;

/**
 * @author bbhou
 * @date 2017/8/1
 */
public class SqlServerJdbcMapper extends AbstractJdbcMapper {

    public SqlServerJdbcMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    public String getColumn(String column) {
        if(KeywordUtil.isOracleKeyword(column)) {
            return "["+column+"]";
        }
        return column;
    }

    @Override
    protected String getScriptPath() {
        return PathConstant.Sql.SQL_SERVER;
    }

    @Override
    protected String driverName() {
        return DriverNameConstant.ORACLE;
    }

    @Override
    protected String selectAllTablesSql() {
        return "SELECT _obj.Name, convert(varchar(100), _ext.value) FROM SysObjects AS _obj " +
                "left join sys.extended_properties AS _ext " +
                "on _obj.id = _ext.major_id and _ext.minor_id=0" +
                "Where _obj.XType='U'";
    }

    @Override
    protected String selectAllFieldsSql(String tableName) {
        return "SELECT _col.TABLE_SCHEMA, _col.COLUMN_NAME, _col.IS_NULLABLE, _col.DATA_TYPE, convert(varchar(100), _ext.value) " +
                "FROM SysObjects AS _sys LEFT JOIN sys.extended_properties AS _ext " +
                "ON _sys.id=_ext.major_id, INFORMATION_SCHEMA.columns AS _col WHERE _col.TABLE_NAME='"+tableName+"' and _sys.name='"+tableName+"' " +
                "AND _col.ORDINAL_POSITION=_ext.minor_id";
    }

}
