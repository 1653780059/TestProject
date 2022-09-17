package com.example.springsecurity.handler;

import cn.hutool.json.JSONUtil;
import com.example.springsecurity.domain.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname MyAuthenticationHandler
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/11 14:10
 * @Created by 16537
 */
@Component
public class MyAuthenticationExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = new Result().no(authException.getMessage());
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
