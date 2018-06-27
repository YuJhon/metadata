package com.ryo.metadata.web.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;

/**
 * @author houbinbin
 * @since 16/10/28
 */
@Controller
@RequestMapping("/")
@Log4j2
public class IndexController {

    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

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
    public String index(Model model) throws URISyntaxException {
        return INDEX_PATH;
    }

}
