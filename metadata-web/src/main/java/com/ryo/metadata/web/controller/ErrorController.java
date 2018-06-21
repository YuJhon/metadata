package com.ryo.metadata.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author houbinbin
 * @since 16/10/28
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

  private static final String BASE_PATH = "error";
  private static final String PAGE_400 = BASE_PATH+"/400";
  private static final String PAGE_403 = BASE_PATH+"/403";
  private static final String PAGE_404 = BASE_PATH+"/404";
  private static final String PAGE_500 = BASE_PATH+"/500";

  @RequestMapping("/400")
  public String page400() {
    return PAGE_400;
  }

  @RequestMapping("/403")
  public String page403() {
    return PAGE_403;
  }

  @RequestMapping("/404")
  public String page404() {
    return PAGE_404;
  }

  @RequestMapping("/500")
  public String page500() {
    return PAGE_500;
  }

}
