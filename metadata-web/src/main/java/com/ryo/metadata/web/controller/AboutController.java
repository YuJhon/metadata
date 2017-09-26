package com.ryo.metadata.web.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author houbinbin
 * @since 16/10/28
 */
@Controller
@RequestMapping("/about")
@Log4j2
public class AboutController {

    private static final String BASE_PATH = "about/";

    /**
     * 页面路径,统一定义
     */
    private static final String INDEX_PATH = "index";
    private static final String CHANGE_LOG_PATH = "changelog";

    /**
     * 首页
     * hard code
     */
    @RequestMapping
    public String index(Model model) {
        return BASE_PATH + INDEX_PATH;
    }

    /**
     * 变更日志
     * @param model
     * @return
     */
    @RequestMapping("/changelog")
    public String changeLog(Model model) {
        return BASE_PATH + CHANGE_LOG_PATH;
    }

}
