package com.ops.sunwou.exception;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class ExceptionResolver extends SimpleMappingExceptionResolver {

    public static final Gson gson = new Gson();

    public static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("code", false);
            if (ex instanceof RuntimeException) {
                result.put("msg", ex.getMessage());
            } else if (ex instanceof JsonSyntaxException) {
                result.put("msg", "json格式错误");
            } else {
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement error : ex.getStackTrace()) {
                    sb.append(ex + "-" + error.toString()).append("\n");
                }
                result.put("msg", "");
            }
            //此行必加，否则返回的json在浏览器中看到是乱码，不易于识别
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.setContentType("text/xml;charset=utf-8");
            response.getWriter().write(gson.toJson(result));
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}