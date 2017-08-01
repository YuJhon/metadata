package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.domain.MetaField;
import com.ryo.metadata.core.domain.MetaModel;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
public class MySqlDBMapper implements DBMapper {

    private static final JdbcMapper JDBC_MAPPER = new MySqlJdbcMapper();

    @Override
    public List<MetaModel> selectAllTables() {
        List<MetaModel> metaModelList = new LinkedList<>();

        try {
//            DatabaseMetaData databaseMetaData = JDBC_MAPPER.metaData();
//            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            Connection connection = JDBC_MAPPER.metaData().getConnection();
            String dbName = connection.getCatalog();    //数据库名称
            String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE TABLE_SCHEMA='"+dbName+"';";
            ResultSet resultSet = JDBC_MAPPER.query(sql);
            while(resultSet.next()) {
                String tableName = resultSet.getString(1);  //表名称
                String comment = resultSet.getString(2);    //注释
                MetaModel metaModel = new MetaModel();
                metaModel.setName(tableName);
                metaModel.setDescription(comment);
                metaModel.setCreateTime(new Date());
                metaModel.setUpdateTime(new Date());
                metaModelList.add(metaModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return metaModelList;
    }


    @Override
    public List<MetaField> selectAllFields(String tableName) {
        List<MetaField> metaFieldList = new LinkedList<>();

        try {
            String dbName = JDBC_MAPPER.metaData().getConnection().getCatalog();
            String sql = "SELECT TABLE_SCHEMA, COLUMN_NAME, IS_NULLABLE, DATA_TYPE, COLUMN_COMMENT " +
                    "FROM information_schema.columns where TABLE_NAME='"+tableName+"' AND TABLE_SCHEMA='"+dbName+"';";
            ResultSet resultSet = JDBC_MAPPER.query(sql);
            while (resultSet.next()) {
                String dbObjectName = resultSet.getString(1)+"."+tableName;
                String columnName = resultSet.getString(2);
                String isNullableStr = resultSet.getString(3);
                String dataType = resultSet.getString(4);
                String comment = resultSet.getString(5);

                MetaField metaField = new MetaField();
                metaField.setName(columnName);
                metaField.setDataType(dataType);
                metaField.setNullable(getBoolVal(isNullableStr));
                metaField.setDescription(comment);
                metaField.setDbObjectName(dbObjectName);
                metaField.setCreateTime(new Date());
                metaField.setUpdateTime(new Date());
                metaFieldList.add(metaField);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return metaFieldList;
    }

    /**
     * 获取对应的bool值
     *
     * @param string
     * @return
     */
    private boolean getBoolVal(String string) {
        if ("YES".equals(string)) {
            return true;
        } else {
            return false;
        }
    }

}
