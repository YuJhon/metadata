package com.ryo.metadata.web.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 元数据控制层
 *
 * URI 约定规范。
 * 1. 禁止下划线、大写字母。
 * 2. 建议使用 - 进行分割
 * 3.
 *
 * 2. 建议使用复数(神奇的建议)
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
        return BASE_PATH+MYSQL_PATH;
    }

    @RequestMapping("/sql-server")
    public String sqlServer(Model model) {
        return BASE_PATH+SQL_SERVER_PATH;
    }

}
