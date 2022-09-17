package com.example.springsecurity.controller;

import com.example.springsecurity.domain.Result;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Classname UserLoginController
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 18:47
 * @Created by 16537
 */
@RestController
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    @PostMapping("/user/login")
    public Result userLogin(@RequestBody SysUser sysUser){
        return userLoginService.login(Optional.ofNullable(sysUser.getUserName()),Optional.ofNullable(sysUser.getPassword()));
    }
}
