package com.example.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 15:01
 * @Created by 16537
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('sys:dept:list')")
    public String hello(){
        return "hello";
    }
}
