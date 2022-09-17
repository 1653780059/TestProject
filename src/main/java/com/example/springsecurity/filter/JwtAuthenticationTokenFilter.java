package com.example.springsecurity.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.domain.UserLoginDetails;
import com.example.springsecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Classname JwtAuthenticationTokenFilter
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 19:40
 * @Created by 16537
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token==null){
            filterChain.doFilter(request,response);
            return;
        }
        // jwt token 解析 出userId
        JWT jwt = JwtUtil.parseToken(token);
        Object userId = jwt.getPayload("userId");
        // 从redis中取出usr信息
        String key = "login:"+ userId;
        //取出的是UserLoginDetails对象的json字符串
        String jsonStr = stringRedisTemplate.opsForValue().get(key);
        if(jsonStr==null || jsonStr.isEmpty()){

            throw new RuntimeException("token非法");
        }
        stringRedisTemplate.expire(key,20,TimeUnit.MINUTES);
        UserLoginDetails user = JSONUtil.toBean(jsonStr, UserLoginDetails.class,true);

        // 存入SecurityContextHolder域中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
        //放行

    }
}
