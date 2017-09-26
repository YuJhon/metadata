package com.ryo.metadata.web.controller;

import com.ryo.metadata.web.dto.bean.CategoryBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;


/**
 * @author houbinbin
 * @since 16/10/30
 */
@Controller
@RequestMapping("/category")
@Log4j2
public class CategoryController {

  private static final String NAV_BAR_PATH = "include/navbar";
  private static final String INDEX = "index";
  private static final String ARTICLE = "article";

  /**
   * 显示顶部导航栏
   * @return
   */
  @RequestMapping("/navbar")
  public String showTopNavBar(Model model) {
    List<CategoryBean> categoryList = getCategoryList();
    model.addAttribute("categoryList", categoryList);
    return NAV_BAR_PATH;
  }

  private List<CategoryBean> getCategoryList() {
    List<CategoryBean> categoryBeanList = new LinkedList<>();
    CategoryBean categoryBean = new CategoryBean();
    categoryBean.setCode("");
    categoryBean.setName("首页");

    CategoryBean categoryBean2 = new CategoryBean();
    categoryBean2.setCode("scan");
    categoryBean2.setName("扫描");

    categoryBeanList.add(categoryBean);
    categoryBeanList.add(categoryBean2);
    return categoryBeanList;
  }




}
