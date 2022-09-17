package com.example.springsecurity.handler;

import cn.hutool.json.JSONUtil;
import com.example.springsecurity.domain.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname MyAuthorizeExceptionHandler
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/11 14:12
 * @Created by 16537
 */
@Component
public class MyAuthorizeExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result result = new Result().no(accessDeniedException.getMessage());
        String string = JSONUtil.toJsonStr(result);
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
