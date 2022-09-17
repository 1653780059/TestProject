package com.example.springsecurity.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import jdk.nashorn.internal.parser.Token;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Classname JWTUtil
 * @Description
 * @Version 1.0.0
 * @Date 2022/9/10 23:32
 * @Created by 16537
 */
public class JwtUtil {
    private static String signer = "@wun$dsaiuw^dsaoxkksefia&";

    public static String createToken(Map<String, Object> map){

        JWTSigner hs256 = JWTSignerUtil.createSigner("HS256", signer.getBytes(StandardCharsets.UTF_8));
        return JWTUtil.createToken(map,hs256);


    }
    public static JWT parseToken(String token) {
        return JWTUtil.parseToken(token);
    }
    public static boolean verify (String token){
        return JWTUtil.verify(token,signer.getBytes(StandardCharsets.UTF_8));
    }
}
