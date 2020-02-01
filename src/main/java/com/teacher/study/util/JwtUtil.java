package com.teacher.study.util;

import com.teacher.study.enetity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private static String key="teacher";
    private static long ttl;

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        JwtUtil.key = key;
    }

    public static long getTtl() {
        return ttl;
    }

    public static void setTtl(long ttl) {
        JwtUtil.ttl = ttl;
    }

    /**
     * 生成Token
     * @param user
     * @return
     */
    public static String createJwt(User user){
        long nowMillis=System.currentTimeMillis();
        Date now=new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(user.getId().toString())//用户ID
                .setSubject(user.getName())//用户名
                .setIssuedAt(now)//生成时间
                .claim("user",user)
                .signWith(SignatureAlgorithm.HS256, key)//前面算法，这里采用HS256加密算法盐值是java
                .setExpiration(new Date(new Date().getTime()+60000*3000));//失效时间(毫秒为单位)这里是一分钟超过时间抛出io.jsonwebtoken
        return builder.compact();
    }





    /**
     * 解析Token
     * @param jwtstr Token
     * @return
     */
    public static Claims parseJWT(String jwtstr){
        Claims body=null;
        try {
            body = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwtstr)
                    .getBody();
        }catch (Exception e){
            return null;
        }
        return body;
    }
}
