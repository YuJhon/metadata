package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.dal.DBMapper;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.domain.MetaField;
import com.ryo.metadata.core.domain.MetaModel;
import com.ryo.metadata.core.service.IdGenerator;
import com.ryo.metadata.core.service.impl.UUIDGenerator;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据库访问层
 * 1.对于ID的生成策略可以暴露接口。
 * Created by bbhou on 2017/8/2.
 */
public abstract class AbstractDBMapper implements DBMapper {

    protected JdbcVo jdbcVo;

    public AbstractDBMapper(JdbcVo jdbcVo) {
        this.jdbcVo = jdbcVo;
    }

    private static final Logger LOGGER = LogManager.getLogger(AbstractDBMapper.class);

    private static final IdGenerator idGenerator = new UUIDGenerator();

    protected abstract JdbcMapper getJdbcMapper();

    /**
     * 获取所有表信息的SQL
     * @return
     */
    protected abstract String selectAllTablesSql();

    /**
     * 获取所有字段信息的SQL\
     * @param tableName 表名称
     * @return
     */
    protected abstract String selectAllFieldsSql(String tableName);

    /**
     * 获取数据库名称
     * @since 1.7 TWR
     * @return
     */
    protected String getDatabaseName() {
        try(Connection connection = getJdbcMapper().metaData().getConnection()) {
            return connection.getCatalog();    //数据库名称
        } catch (SQLException e) {
            LOGGER.error("getDatabaseName meet ex: "+e, e);
        }
        return null;
    }

    @Override
    public List<MetaModel> selectAllTables() {
        List<MetaModel> metaModelList = new LinkedList<>();

        try {
//            DatabaseMetaData databaseMetaData = JDBC_MAPPER.metaData();
//            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            String sql = selectAllTablesSql();
            ResultSet resultSet = getJdbcMapper().query(sql);
            while(resultSet.next()) {
                String uid = idGenerator.genId();
                String tableName = resultSet.getString(1);  //表名称
                String comment = resultSet.getString(2);    //注释
                MetaModel metaModel = new MetaModel();
                metaModel.setUid(uid);
                metaModel.setName(tableName);
                metaModel.setDescription(comment);
                metaModel.setCreateTime(new Date());
                metaModel.setUpdateTime(new Date());
                metaModelList.add(metaModel);
            }
        } catch (SQLException e) {
            LOGGER.error("selectAllTables meet ex: "+e, e);
        }

        return metaModelList;
    }

    @Override
    public List<MetaField> selectAllFields(String tableName) {
        List<MetaField> metaFieldList = new LinkedList<>();
        String sql = selectAllFieldsSql(tableName);
        ResultSet resultSet = getJdbcMapper().query(sql);    //指定需要的列信息
        try {
            while(resultSet.next()) {
                String uid = idGenerator.genId();
                String dbObjectName = resultSet.getString(1)+"."+tableName;
                String columnName = resultSet.getString(2);
                String isNullableStr = resultSet.getString(3);
                String dataType = resultSet.getString(4);
                String comment = resultSet.getString(5);

                MetaField metaField = new MetaField();
                metaField.setUid(uid);
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
