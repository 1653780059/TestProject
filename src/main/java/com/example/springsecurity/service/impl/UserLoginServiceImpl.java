package com.example.springsecurity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.springsecurity.domain.Result;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.domain.UserLoginDetails;
import com.example.springsecurity.service.UserLoginService;
import com.example.springsecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Classname UserLoginServiceImpl
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 18:56
 * @Created by 16537
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Result login(Optional<String> username, Optional<String> password) {
        if(!username.isPresent()||!password.isPresent()){
            return new Result().no(new IllegalArgumentException("用户名密码不能为空"));
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserLoginDetails user =(UserLoginDetails) authenticate.getPrincipal();
        String jsonStr = JSONUtil.toJsonStr(user);
        Long id = user.getUser().getId();
        //存入redis的是UserLoginDetails对象
        redisTemplate.opsForValue().set("login:"+ id,jsonStr,20, TimeUnit.MINUTES);
        // 对user的ID通过JWT加密并传递给前端

        Map<String, Object> map = new HashMap<>();
        map.put("userId",id);
        String token = JwtUtil.createToken(map);
        map.remove("userId");
        map.put("token",token);

        return new Result().ok(token);

    }
}
