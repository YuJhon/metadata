package com.ryo.metadata.core.util;

import com.ryo.metadata.core.constant.DBClassNameConstant;
import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

/**
 * 数据库脚本执行工具类
 * 这里应该重写，见：
 * @author houbinbin
 * @see com.ryo.metadata.core.dal.impl.MySqlDBMapper
 * @see com.ryo.metadata.core.dal.impl.SqlServerDBMapper
 */
public class MybatisSqlExecUtil {

    private static final Logger LOGGER = LogManager.getLogger(MybatisSqlExecUtil.class);

    static {
        try {
            Class.forName(DBClassNameConstant.MYSQL).newInstance();
            //TODO: 这个真实部署到 tomcat 会报错！
            Class.forName(DBClassNameConstant.SQL_SERVER).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.error("Init jdbc driver meet ex: " + e, e);
        }
    }

    /**
     * 执行
     *
     * @param jdbcVo      数据库连接信息
     * @param inputStream 待执行的脚本信息
     */
    public static void execute(JdbcVo jdbcVo, InputStream inputStream) throws Exception {
        final String url = jdbcVo.getUrl();
        final String username = jdbcVo.getUsername();
        final String password = jdbcVo.getPassword();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            //TODO: 加在驱动时  前端会直接结束！为什么？
            Reader reader = new InputStreamReader(inputStream);
            ScriptRunner runner = new ScriptRunner(conn);
            //设置字符集,不然中文乱码插入错误
            Resources.setCharset(StandardCharsets.UTF_8);
            //设置是否输出日志
            runner.setLogWriter(new PrintWriter(System.out));
            runner.runScript(reader);
            runner.closeConnection();
        }
    }


}
