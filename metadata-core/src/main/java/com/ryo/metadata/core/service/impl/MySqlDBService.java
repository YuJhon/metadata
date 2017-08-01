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
import java.util.List;

/**
 * todo:
 * 1. 插入之前对表进行初始化。
 * 2. 补充部分字段(uid)的信息
 * Created by bbhou on 2017/8/1.
 */
public class MySqlDBService implements DBService {

    private static final DBMapper DB_MAPPER = new MySqlDBMapper();

    private static final JdbcMapper JDBC_MAPPER = new MySqlJdbcMapper();

    @Override
    public void createMetaModelData() {
        List<MetaModel> metaModelList = DB_MAPPER.selectAllTables();

        for(MetaModel metaModel : metaModelList) {
            try {
                String sql = DBSqlUtil.insert("meta_model", metaModel, null);
                JDBC_MAPPER.execute(sql);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void createMetaFieldData() {
        List<MetaModel> metaModelList = DB_MAPPER.selectAllTables();
        for(MetaModel model : metaModelList) {
            String name = model.getName();
            List<MetaField> metaFieldList = DB_MAPPER.selectAllFields(name);

            for(MetaField metaField : metaFieldList) {
                String sql = null;
                try {
                    sql = DBSqlUtil.insert("meta_field", metaField, null);
                    JDBC_MAPPER.execute(sql);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
