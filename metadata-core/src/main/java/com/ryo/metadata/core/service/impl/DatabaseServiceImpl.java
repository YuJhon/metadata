/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.constant.EntityConstant;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.dal.impl.MySqlJdbcMapper;
import com.ryo.metadata.core.dal.impl.OracleJdbcMapper;
import com.ryo.metadata.core.dal.impl.SqlServerJdbcMapper;
import com.ryo.metadata.core.domain.JdbcVo;
import com.ryo.metadata.core.domain.MetaField;
import com.ryo.metadata.core.domain.MetaModel;
import com.ryo.metadata.core.exception.MetadataRuntimeException;
import com.ryo.metadata.core.service.IDatabaseService;
import com.ryo.metadata.core.util.DatabaseSqlUtil;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/21 下午8:22  </pre>
 * <pre> Project: metadata  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {

    @Override
    public void executeMysql(JdbcVo jdbcVo) {
        final JdbcMapper mysql = new MySqlJdbcMapper(jdbcVo);
        execute(mysql);
    }

    @Override
    public void executeOracle(JdbcVo jdbcVo) {
        final JdbcMapper mysql = new OracleJdbcMapper(jdbcVo);
        execute(mysql);
    }

    @Override
    public void executeSqlServer(JdbcVo jdbcVo) {
        final JdbcMapper sqlServer = new SqlServerJdbcMapper(jdbcVo);
        execute(sqlServer);
    }

    private void execute(final JdbcMapper jdbcMapper) {
        try {
            //1. 创建表
            jdbcMapper.createMetadataTables();

            //2. 创建实体
            createMetaModelData(jdbcMapper);

            //3. 创建字段
            createMetaFieldData(jdbcMapper);
        } catch (IllegalAccessException | SQLException e) {
            throw new MetadataRuntimeException(e);
        }
    }

    /**
     * 创建实体数据
     * @throws IllegalAccessException 异常 非法访问异常
     * @throws SQLException 异常 数据库异常
     */
    private void createMetaModelData(JdbcMapper jdbcMapper) throws IllegalAccessException, SQLException {
        List<MetaModel> metaModelList = jdbcMapper.selectAllTables();

        List<String> sqlList = new LinkedList<>();
        String truncateTableSql = DatabaseSqlUtil.truncateTable(EntityConstant.META_MODEL);
        sqlList.add(truncateTableSql);
        for (MetaModel metaModel : metaModelList) {
            String insertSql = DatabaseSqlUtil.insert(jdbcMapper, EntityConstant.META_MODEL, metaModel, null);
            sqlList.add(insertSql);
        }

        jdbcMapper.executeBatchTx(sqlList);
    }

    /**
     * 创建实体字段数据
     * @throws IllegalAccessException 异常 非法访问异常
     * @throws SQLException 异常 数据库异常
     */
    private void createMetaFieldData(JdbcMapper jdbcMapper) throws IllegalAccessException, SQLException {
        List<MetaModel> metaModelList = jdbcMapper.selectAllTables();
        List<String> sqlList = new LinkedList<>();
        String truncateTableSql = DatabaseSqlUtil.truncateTable(EntityConstant.META_FIELD);
        sqlList.add(truncateTableSql);
        for (MetaModel model : metaModelList) {
            String name = model.getName();
            List<MetaField> metaFieldList = jdbcMapper.selectAllFields(name);
            for (MetaField metaField : metaFieldList) {
                String insertSql = DatabaseSqlUtil.insert(jdbcMapper, EntityConstant.META_FIELD,
                        metaField, null);
                sqlList.add(insertSql);
            }
        }
        jdbcMapper.executeBatchTx(sqlList);
    }

}
