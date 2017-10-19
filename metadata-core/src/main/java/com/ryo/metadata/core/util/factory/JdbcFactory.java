package com.ryo.metadata.core.util.factory;

import com.ryo.medata.util.util.PathUtil;
import com.ryo.metadata.core.util.PropertiesIOUtil;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 生成对应的JDBC信息
 * Created by bbhou on 2017/8/1.
 */
@Deprecated
public class JdbcFactory {

    private static final Logger LOGGER = LogManager.getLogger(JdbcFactory.class);

    /**
     * mysql
     */
    private static JdbcVo _mysql = null;

    /**
     * sql server
     */
    private static JdbcVo _sqlServer = null;

    /**
     * 单例获取 mysql 的数据库配置信息
     * @return
     * @throws ClassNotFoundException
     */
    public static JdbcVo getMySql(JdbcVo jdbcVo) throws ClassNotFoundException {
        if(_mysql == null) {
            String propertyPath = PathUtil.getPath()+"/src/main/resources/jdbc_mysql.properties";
            LOGGER.info("getMySql with file: "+propertyPath);
            _mysql = buildJdbcVo(propertyPath);
        }
        return _mysql;
    }

    /**
     * 单例获取 Sql Server 的数据库配置信息
     * @return
     * @throws ClassNotFoundException
     */
    public static JdbcVo getSqlServer() throws ClassNotFoundException {
        if(_sqlServer == null) {
            String propertyPath = PathUtil.getPath()+"/src/main/resources/jdbc_sqlserver.properties";
            LOGGER.info("getSqlServer with file: "+propertyPath);
            _sqlServer = buildJdbcVo(propertyPath);
        }
        return _sqlServer;
    }

    /**
     * 构建JDBC对象
     * @param propertyPath 配置文件路径
     * @return
     */
    private static JdbcVo buildJdbcVo(String propertyPath) throws ClassNotFoundException {
        PropertiesIOUtil.loadProperties(propertyPath);

        JdbcVo jdbcVo = new JdbcVo();
        jdbcVo.setDriverClassName(PropertiesIOUtil.getProperty("jdbc.driverClassName"));
        jdbcVo.setUrl(PropertiesIOUtil.getProperty("jdbc.url"));
        jdbcVo.setUsername(PropertiesIOUtil.getProperty("jdbc.username"));
        jdbcVo.setPassword(PropertiesIOUtil.getProperty("jdbc.password"));
        return jdbcVo;
    }

}
