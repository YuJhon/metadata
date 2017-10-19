package com.ryo.metadata.web.controller;

import com.ryo.metadata.core.service.DBService;
import com.ryo.metadata.core.service.impl.MySqlDBService;
import com.ryo.metadata.core.util.vo.JdbcVo;
import com.ryo.metadata.web.dto.response.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 元数据控制层
 * <p>
 * URI 约定规范。
 * 1. 禁止下划线、大写字母。
 * 2. 建议使用 - 进行分割
 * 3.
 * <p>
 * 2. 建议使用复数(神奇的建议)
 *
 * @author houbinbin
 * @since 16/10/28
 */
@Controller
@RequestMapping("/metadata")
@Log4j2
public class MetadataController {

    private static final String BASE_PATH = "metadata/";

    /**
     * 页面路径,统一定义
     */
    private static final String MYSQL_PATH = "mysql";
    private static final String SQL_SERVER_PATH = "sql-server";


    @RequestMapping("/mysql")
    public String mysql(Model model) {
        return BASE_PATH + MYSQL_PATH;
    }

    @RequestMapping("/sql-server")
    public String sqlServer(Model model) {
        return BASE_PATH + SQL_SERVER_PATH;
    }


    @RequestMapping(value = "/mysql/execute", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse mysqlExecute(@RequestParam(value = "host", required = false, defaultValue = "127.0.0.1") String host,
                                     @RequestParam(value = "port", required = false, defaultValue = "3306") String port,
                                     @RequestParam(value = "database", required = true) String database,
                                     @RequestParam(value = "username", required = false, defaultValue = "root") String username,
                                     @RequestParam(value = "password", required = false, defaultValue = "123456") String password) {

        log.info("start...");
        BaseResponse baseResponse = new BaseResponse();
        JdbcVo jdbcVo = buildMySqlJdbcVo(host, port, database, username, password);

        DBService dbService = new MySqlDBService(jdbcVo);
        try {
            dbService.initMetadataTables();
            log.info("After init tables....");
            dbService.execute();
            baseResponse.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setSuccess(false);
        }

        return baseResponse;
    }


    /**
     * Mysql 的链接信息
     *
     * @return
     */
    private JdbcVo buildMySqlJdbcVo(final String host, final String port,
                                    final String database, final String username, final String password) {
        JdbcVo jdbcVo = new JdbcVo();
        jdbcVo.setDriverClassName("com.mysql.jdbc.Driver");
        jdbcVo.setUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&useOldAlias", host, port, database));
        jdbcVo.setUsername(username);
        jdbcVo.setPassword(password);
        log.info("jdbcVo: "+jdbcVo);
        return jdbcVo;
    }

//    jdbc.driverClassName=com.mysql.jdbc.Driver
//    jdbc.url=jdbc:mysql://127.0.0.1:3306/fate?useUnicode=true&characterEncoding=UTF-8&useOldAlias
//    jdbc.username=root
//    jdbc.password=123456789
}
