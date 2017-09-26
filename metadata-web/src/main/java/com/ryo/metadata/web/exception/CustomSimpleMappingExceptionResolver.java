package com.ryo.metadata.web.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by houbinbin on 16/6/24.
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler, Exception ex) {
        String viewName = determineViewName(ex, request);
        if (viewName != null) {
            if (!(request.getHeader("accept").contains("application/json") || (request
                    .getHeader("X-Requested-With") != null && request
                    .getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
                // 如果不是异步请求
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            } else {
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(ex.getMessage());
                    writer.flush();
                } catch (IOException e) {
                    logger.error("出错了!", e);
                }
                return null;

            }
        } else {
            return null;
        }
    }
}
