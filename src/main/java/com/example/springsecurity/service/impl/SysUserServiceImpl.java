package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.service.SysUserService;
import com.example.springsecurity.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 16537
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-09-10 19:03:18
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




