package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.util.vo.JdbcVo;

/**
 * SQL Server 数据库访问层
 * [](http://www.cnblogs.com/songxingzhu/p/5849029.html)
 * Created by bbhou on 2017/7/31.
 */
public class SqlServerDBMapper extends AbstractDBMapper {

    public SqlServerDBMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected JdbcMapper getJdbcMapper() {
        return new SqlServerJdbcMapper(jdbcVo);
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
        String sql = "SELECT _col.TABLE_SCHEMA, _col.COLUMN_NAME, _col.IS_NULLABLE, _col.DATA_TYPE, convert(varchar(100), _ext.value) " +
                "FROM SysObjects AS _sys LEFT JOIN sys.extended_properties AS _ext " +
                "ON _sys.id=_ext.major_id, INFORMATION_SCHEMA.columns AS _col WHERE _col.TABLE_NAME='"+tableName+"' and _sys.name='"+tableName+"' " +
                "AND _col.ORDINAL_POSITION=_ext.minor_id";
        return sql;
    }

}
