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
@RequestMapping("/")
@Log4j2
public class IndexController {

    /**
     * 页面路径,统一定义
     */
    private static final String INDEX_PATH = "index";

    private static final String ABOUT_PATH = "about";

    /**
     * 首页
     * hard code
     */
    @RequestMapping
    public String index(Model model) {
        return INDEX_PATH;
    }

}
