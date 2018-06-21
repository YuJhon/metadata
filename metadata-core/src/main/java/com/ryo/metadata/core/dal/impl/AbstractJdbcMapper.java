package com.ryo.metadata.core.dal.impl;

import com.ryo.medata.util.util.IDUtil;
import com.ryo.metadata.core.dal.JdbcMapper;
import com.ryo.metadata.core.domain.JdbcVo;
import com.ryo.metadata.core.domain.MetaField;
import com.ryo.metadata.core.domain.MetaModel;
import com.ryo.metadata.core.util.CoreSqlPathUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bbhou
 * @date 2017/8/1
 */
public abstract class AbstractJdbcMapper implements JdbcMapper {

    private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcMapper.class);

    protected JdbcVo jdbcVo;

    public AbstractJdbcMapper(JdbcVo jdbcVo) {
        this.jdbcVo = jdbcVo;
        try {
            final String driverName = driverName();
            Class.forName(driverName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取脚本路径
     * @return
     */
    protected abstract String getScriptPath();

    /**
     * 获取驱动名称
     * @return
     */
    protected abstract String driverName();

    /**
     * 获取所有表信息的SQL
     *
     * @return
     */
    protected abstract String selectAllTablesSql();

    /**
     * 获取所有字段信息的SQL\
     *
     * @param tableName 表名称
     * @return
     */
    protected abstract String selectAllFieldsSql(String tableName);

    /**
     * 获取数据库连接
     * @return 数据库链接
     */
    protected Connection getConnection() {
        try {
            Class.forName(jdbcVo.getDriverClassName());
            return DriverManager.getConnection(jdbcVo.getUrl(),
                    jdbcVo.getUsername(),
                    jdbcVo.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据库名称
     *
     * @return
     * @since 1.7 TWR
     */
    protected String getDatabaseName() {
        try (Connection connection = getConnection()) {
            //数据库名称
            return connection.getCatalog();
        } catch (SQLException e) {
            LOGGER.error("getDatabaseName meet ex: " + e, e);
        }
        return null;
    }

    @Override
    public ResultSet query(String querySql) {
        ResultSet rs = null;
        Connection connection = getConnection();
        try {
            Statement stmt = null;
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
        } catch (Exception e) {
            LOGGER.error("query meet ex: "+e, e);
        }
        return rs;
    }

    @Override
    public void execute(String sql) {
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            LOGGER.error("execute meet ex: "+e, e);
        }
    }

    @Override
    public void executeBatch(List<String> stringList) {
        LOGGER.info("executeBatch with sql: "+ stringList);

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            for (String sql : stringList) {
                statement.addBatch(sql);
                LOGGER.debug(sql);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("executeBatch meet ex: " + e, e);
        }

    }

    @Override
    public void executeBatchTx(List<String> stringList) throws SQLException {
        LOGGER.info("executeBatchTx with sql: "+ stringList);
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            for(String sql : stringList) {
                if(StringUtils.isBlank(sql)) {
                    continue;
                }
                statement.addBatch(sql);
                LOGGER.debug(sql);
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.error("executeBatchTx meet ex: "+e, e);
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public DatabaseMetaData metaData() throws SQLException {
        return getConnection().getMetaData();
    }

    @Override
    public List<MetaModel> selectAllTables() {
        List<MetaModel> metaModelList = new LinkedList<>();

        try {
            String sql = selectAllTablesSql();
            ResultSet resultSet = this.query(sql);
            while (resultSet.next()) {
                String uid = IDUtil.uuid();
                //表名称
                String tableName = resultSet.getString(1);
                //注释
                String comment = resultSet.getString(2);
                MetaModel metaModel = new MetaModel();
                metaModel.setUid(uid);
                metaModel.setName(tableName);
                metaModel.setDescription(comment);
                metaModel.setCreateTime(new java.util.Date());
                metaModel.setUpdateTime(new java.util.Date());
                metaModelList.add(metaModel);
            }
        } catch (SQLException e) {
            LOGGER.error("selectAllTables meet ex: " + e, e);
        }

        return metaModelList;
    }

    @Override
    public List<MetaField> selectAllFields(String tableName) {
        List<MetaField> metaFieldList = new LinkedList<>();
        String sql = selectAllFieldsSql(tableName);
        //指定需要的列信息
        ResultSet resultSet = this.query(sql);
        try {
            while (resultSet.next()) {
                String uid = IDUtil.uuid();
                String dbObjectName = resultSet.getString(1) + "." + tableName;
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
                metaField.setCreateTime(new java.util.Date());
                metaField.setUpdateTime(new Date());
                metaFieldList.add(metaField);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return metaFieldList;
    }

    @Override
    public void createMetadataTables() {
        final String scriptPath = getScriptPath();
        final String url = jdbcVo.getUrl();
        final String username = jdbcVo.getUsername();
        final String password = jdbcVo.getPassword();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             InputStream inputStream = CoreSqlPathUtil.getInputStream(scriptPath);
        ) {
            //TODO: 加在驱动时  前端会直接结束！为什么？
            Reader reader = new InputStreamReader(inputStream);
            ScriptRunner runner = new ScriptRunner(conn);
            //设置字符集,不然中文乱码插入错误
            Resources.setCharset(StandardCharsets.UTF_8);
            //设置是否输出日志
            runner.setLogWriter(new PrintWriter(System.out));
            runner.runScript(reader);
            runner.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }
        if ("Y".equals(string)) {
            return true;
        } else {
            return false;
        }
    }
}
