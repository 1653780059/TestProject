package com.example.springsecurity.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname UserLoginDetails
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 19:08
 * @Created by 16537
 */
@Data

@NoArgsConstructor
public class UserLoginDetails implements UserDetails {
    private SysUser user;
    private List<String> permission;
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;
    public UserLoginDetails (SysUser user,List<String> permission){
        this.user=user;
        this.permission=permission;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
       authorities=permission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
