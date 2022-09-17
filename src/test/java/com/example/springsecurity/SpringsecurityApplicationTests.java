package com.example.springsecurity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.HMacJWTSigner;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.mapper.SysUserMapper;
import com.example.springsecurity.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootTest
class SpringsecurityApplicationTests {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    void contextLoads() {
        System.out.println(bCryptPasswordEncoder.encode("1234"));
        System.out.println(bCryptPasswordEncoder.matches("1234","$2a$10$d0eAmt6Q42vHiyEJVG2lGeWY8EPGYzEs0lOU7Y8FUAWcqhtl92DUm"));
    }
    @Test
    void testJwt(){
        final String signer = "2#iewo%idal*(eklawl&dwio@";
        Map<String,Object> map = new HashMap<>();
        map.put("userId","12");
        String token = JWTUtil.createToken(map, signer.getBytes(StandardCharsets.UTF_8));
        System.out.println(token);
        JWT jwt = JWTUtil.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMiJ9.h8OR0yay7cCgKPOgn8TBP1BIuZFWP2N28hEV0F0N8vY");
        System.out.println(jwt.getPayload("userId"));


    }
    @Test
    void testJWTUtils(){
        String signer="eiiojdsafmkewioj";
        String userId="12";
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        System.out.println(JwtUtil.createToken(map));
        System.out.println(JwtUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMiJ9.q-n_xTUG8kHkAetTrRyVfA-ZpPeFYaD97GCMfPXXx44"));
        JWT jwt = JwtUtil.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMiJ9.q-n_xTUG8kHkAetTrRyVfA-ZpPeFYaD97GCMfPXXx44");
        System.out.println(jwt.getPayload("userId"));


    }
    @Test
    void test(){
        System.out.println(sysUserMapper.getPermission("1"));
    }
    @Test
    void testOptional(){
        /*String key = "test:Optional";
        Optional<String> key1 = Optional.ofNullable(key);
        key1.ifPresent(k->{
            stringRedisTemplate.opsForValue().set(k,"key");
        });*/


       /* String Null = null;
        Optional<String> aNull = Optional.ofNullable(Null);

        try {
            aNull.orElseThrow(()->{throw new RuntimeException("Null值");});

        }catch (Throwable e){
            System.out.println(e.getMessage());
        }*/

        /*String username = "czq";
        String password = "null";
        Optional<String> username1 = Optional.ofNullable(username);
        Optional<String> password1 = Optional.ofNullable(password);
        if(username1.isPresent()&&password1.isPresent()){
            System.out.println(username1+","+password1);
        }*/
        Optional<SysUser> one = sysUserMapper.getOne("2");
        one.ifPresent(System.out::println);
    }
    @Test
    void testRedisExecute(){
        try {
            Properties properties = (Properties)stringRedisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
            Properties properties1=(Properties) stringRedisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats") );
            Object o=stringRedisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
            System.out.println(properties);
            System.out.println(properties1);
            System.out.println(o);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void testSystemInfo(){
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        System.out.println(hardware.getComputerSystem());
        List<HWDiskStore> diskStores = hardware.getDiskStores();

    }

}
