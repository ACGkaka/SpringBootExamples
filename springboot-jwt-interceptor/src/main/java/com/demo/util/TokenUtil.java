package com.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.user.entity.User;
import org.apache.logging.log4j.util.Strings;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title TokenUtil
 * <p> @Description Token 工具类
 *
 * @author ACGkaka
 * @date 2021/5/6 14:15
 */
public class TokenUtil {

    public static final String JWT_SECRET_KEY = "testjwt";

    /**
     * 创建Token
     *
     * @param user 用户实体
     * @return Token
     */
    public static String createToken(User user) {

        // 登录成功后生成JWT
        // JWT的header部分,该map可以是空的,因为有默认值{"alg":HS256,"typ":"JWT"}
        Map<String, Object> map = new HashMap<>();

        // 30分钟过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,30);

        return JWT.create()
                // 添加头部
                .withHeader(map)
                // 添加payload
                .withClaim("userId",user.getId())
                .withClaim("username",user.getUsername())
                .withClaim("email",user.getEmail())
                // 设置过期时间
                .withExpiresAt(instance.getTime())
                // 设置签名 密钥
                .sign(Algorithm.HMAC256(JWT_SECRET_KEY));
    }

    /**
     * 检查Token是否有效
     *
     * @param token Token
     * @return 是否有效
     */
    public static boolean isValid(String token) {
        if (Strings.isNotBlank(token)) {
            try {
                //创建验证对象,这里使用的加密算法和密钥必须与生成TOKEN时的相同否则无法验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRET_KEY)).build();
                //验证JWT
                DecodedJWT decodedJwt = jwtVerifier.verify(token);
                return new Date().before(decodedJwt.getExpiresAt());
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 检查Token所有Claims
     *
     * @param token Token
     * @return Claims
     */
    public static Map<String, Object> getClaims(String token) {

        if (isValid(token))  {
            //创建验证对象,这里使用的加密算法和密钥必须与生成TOKEN时的相同否则无法验证
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRET_KEY)).build();
            //验证JWT
            DecodedJWT decodedJwt = jwtVerifier.verify(token);

            //获取JWT中的数据,注意数据类型一定要与添加进去的数据类型一致,否则取不到数据
            Map<String, Object> map = new HashMap<>();
            map.put("userId", decodedJwt.getClaim("userId").asInt());
            map.put("username", decodedJwt.getClaim("username").asString());
            map.put("email", decodedJwt.getClaim("email").asString());
            map.put("expire", decodedJwt.getExpiresAt());
            return map;
        } else {
            throw new RuntimeException("Token验证失败，请重新登录");
        }
    }
}
