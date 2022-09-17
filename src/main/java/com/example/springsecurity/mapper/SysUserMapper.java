package com.example.springsecurity.mapper;

import com.example.springsecurity.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
* @author 16537
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2022-09-10 19:03:18
* @Entity com.example.springsecurity.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> getPermission(@Param("userId") String userId);
    Optional<SysUser> getOne(@Param("userId") String userId);
}




