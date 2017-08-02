package com.ryo.metadata.core.service.impl;

import com.ryo.medata.util.util.DBSqlUtil;
import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.MySqlDBMapper;
import com.ryo.metadata.core.dal.impl.MySqlJdbcMapper;
import com.ryo.metadata.core.domain.MetaField;
import com.ryo.metadata.core.domain.MetaModel;
import com.ryo.metadata.core.service.DBService;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBService implements DBService {

    private static final DBMapper DB_MAPPER = new MySqlDBMapper();

    private static final JdbcMapper JDBC_MAPPER = new MySqlJdbcMapper();

    @Override
    public void createMetaModelData() throws IllegalAccessException, SQLException {
        List<MetaModel> metaModelList = DB_MAPPER.selectAllTables();

        List<String> sqlList = new LinkedList<>();
        String truncateTableSql = DBSqlUtil.truncateTable("meta_model");
        sqlList.add(truncateTableSql);
        for (MetaModel metaModel : metaModelList) {
            String insertSql = DBSqlUtil.insert("meta_model", metaModel, null);
            sqlList.add(insertSql);
        }

        JDBC_MAPPER.executeTransaction(sqlList);
    }

    @Override
    public void createMetaFieldData() throws IllegalAccessException, SQLException {
        List<MetaModel> metaModelList = DB_MAPPER.selectAllTables();
        List<String> sqlList = new LinkedList<>();
        String truncateTableSql = DBSqlUtil.truncateTable("meta_field");
        sqlList.add(truncateTableSql);
        for (MetaModel model : metaModelList) {
            String name = model.getName();
            List<MetaField> metaFieldList = DB_MAPPER.selectAllFields(name);
            for (MetaField metaField : metaFieldList) {
                String insertSql = DBSqlUtil.insert("meta_field", metaField, null);
                sqlList.add(insertSql);
            }
        }
        JDBC_MAPPER.executeTransaction(sqlList);
    }

}
