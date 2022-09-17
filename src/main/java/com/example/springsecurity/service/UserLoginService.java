package com.example.springsecurity.service;

import com.example.springsecurity.domain.Result;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Classname UserLoginService
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 18:55
 * @Created by 16537
 */

public interface UserLoginService {
    Result login(Optional<String> username,Optional<String>  password);

}
