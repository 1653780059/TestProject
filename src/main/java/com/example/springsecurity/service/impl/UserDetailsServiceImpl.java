package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.domain.UserLoginDetails;
import com.example.springsecurity.mapper.SysUserMapper;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 16537
 * @Classname UserDetalisServiceImpl
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 19:06
 * @Created by 16537
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName,username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if(user==null){
            throw new RuntimeException("用户未注册");
        }
        List<String> permission = sysUserMapper.getPermission(user.getId().toString());
        return new UserLoginDetails(user,permission);
    }
}
