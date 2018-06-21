package com.ryo.metadata.web.controller;

import com.ryo.metadata.web.dto.response.BaseResponse;
import com.ryo.metadata.web.support.jdbc.IJdbc;
import com.ryo.metadata.web.support.jdbc.JdbcContainer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

/**
 * 元数据控制层
 *
 * @author houbinbin
 * @since 16/10/28
 */
@Controller
@RequestMapping("/metadata")
@Log4j2
public class MetadataController {

    private static final String BASE_PATH = "metadata/";

    private static final Logger LOGGER = LogManager.getLogger(MetadataController.class);

    private final JdbcContainer jdbcContainer;

    @Autowired
    public MetadataController(JdbcContainer jdbcContainer) {
        this.jdbcContainer = jdbcContainer;
    }

    /**
     * 页面路径
     *
     * @param path 路径
     * @return 页面路径
     */
    @RequestMapping("{path}")
    public String page(@PathVariable(value = "path") String path) {
        return BASE_PATH + path;
    }


    /**
     * 执行脚本
     *
     * @param host     服务地址
     * @param port     端口号
     * @param database 数据库(oracle 没有数据库的概念)
     * @param username 用户名称
     * @param password 密码
     * @param type     执行的类型
     * @return 执行结果
     */
    @RequestMapping(value = "/{type}/execute", method = RequestMethod.POST)
    public BaseResponse execute(@RequestParam(value = "host", required = false) String host,
                                @RequestParam(value = "port", required = false) String port,
                                @RequestParam(value = "database", required = false) String database,
                                @RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "password", required = false) String password,
                                @PathVariable("type") String type) {
        IJdbc jdbcService = jdbcContainer.getSerivce(type);
        BaseResponse baseResponse = new BaseResponse();
        try {
            jdbcService.execute(host, port, database, username, password);
            baseResponse.setSuccess(true);
        } catch (Exception ex) {
            LOGGER.error("{} Meet ex: ", type, ex);
            baseResponse.setSuccess(false);
            baseResponse.setErrMsg("执行失败！");
        }
        return baseResponse;
    }

}
