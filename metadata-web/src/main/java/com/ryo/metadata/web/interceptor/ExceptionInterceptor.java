package com.ryo.metadata.web.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 侯彬彬
 * @date 2016/7/6
 * preHandle在Controller被调用前，先执行，可以在这里执行一些安全检查（上面示意了如何对IP做限制）

 postHandle在Controller调用后执行，这时，可以修改ModelAndView，比如转到其它view之类

 afterCompletion在Controller调用全部完成后执行，如果ex变量不为空，表示有异常了，这里可以记录异常日志

 afterConcurrentHandlingStarted这个没怎么研究过，暂时不做评价
 */
public class ExceptionInterceptor extends HandlerInterceptorAdapter {
    protected Logger logger = LogManager.getLogger();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(ex != null) {
            logger.error("\n---------------------EXCEPTION START---------------------");
            logger.error(handler);
            logger.error(ex.getMessage(), ex);
            logger.error("\n---------------------EXCEPTION END-----------------------\n");
        }
    }
}
