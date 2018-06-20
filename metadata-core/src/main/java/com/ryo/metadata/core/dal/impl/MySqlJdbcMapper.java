package com.ryo.metadata.core.dal.impl;

import com.ryo.metadata.core.util.vo.JdbcVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bbhou
 * @date 2017/8/1
 */
public class MySqlJdbcMapper extends AbstractJdbcMapper {


    public MySqlJdbcMapper(JdbcVo jdbcVo) {
        super(jdbcVo);
    }

    @Override
    protected Connection getConnection() {
        try {
            Class.forName(jdbcVo.getDriverClassName());
            Connection connection = DriverManager.getConnection(jdbcVo.getUrl(),
                    jdbcVo.getUsername(),
                    jdbcVo.getPassword());
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    必须把mysql-connector-java-5.1.7-bin.jar导入到tomcat的lib目录下面！
//
//    在java项目中，只需要引入mysql-connector-java-5.1.7-bin.jar就可以运行java项目。
//
//    在web项目中，当Class.forName("om.mysql.jdbc.Driver");时myeclipse是不会去查找字符串，不会去查找驱动的。所以只需要把mysql-connector-java-5.1.7-bin.jar拷贝到tomcat下lib目录就可以了。
}
