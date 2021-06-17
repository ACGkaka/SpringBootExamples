# Spring Boot 集成 JWT

<img src="https://img-blog.csdnimg.cn/2021050711093511.png" width="80%" />


> **JWT**
>
> * **JWT(Json Web Token)**，是一种广泛应用于网络环境传输、基于 `JSON` 的开放标准(RFC 7519)，主要应用于`单点登录(SSO)`。
> * **官方网址：**[https://jwt.io/](https://jwt.io/)



## 1.Maven坐标

```xml
<dependency>
      <groupId>com.auth0</groupId>
      <artifactId>java-jwt</artifactId>
      <version>3.4.0</version>
</dependency>
```



## 2.JWT结构

**举个例子：**

```
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjAyODY1NTgsInVzZXJpZCI6MSwiZW1haWwiOiIxMjNAMTIzLmNvbSIsInVzZXJuYW1lIjoiQUNHa2FrYSJ9.UAXvhgPFVcibCWR5WY2CghNRE9aFiZ4Ps8kdFtxhW8k
```
**官网解析结果：**

<img src="https://img-blog.csdnimg.cn/20210506164731957.png" width="80%" />


**JWT 主要包含三个部分：**

* **Header 头部**，包含了 `Token类型` 和 `采用的加密算法`，一般默认取如下值：

  ```json
  { 
    "alg": "HS256",
     "typ": "JWT"
  } 
  ```

* **Payload 负载**，包含三种声明`Claim`：

  * 1）标准注册的声明，如下所示，建议但不强制使用；

    `iss`: jwt签发者
     `sub`: 面向的用户(jwt所面向的用户)
     `aud`: 接收jwt的一方
     `exp`: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
     `nbf`: 定义在什么时间之前，该jwt都是不可用的.
     `iat`: jwt的签发时间
     `jti`: jwt的唯一身份标识，主要用来作为一次性`token`,从而回避重放攻击。

  * 2）公共的声明；

  * 3）私有的声明。

* **Signature 签名**，签名/签证。



## 3.加密算法

> **加密算法是单向函数散列算法，常见的有MD5、SHA、HAMC。**
>
> * **MD5(message-digest algorithm 5)** （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验。校验？不管文件多大，经过MD5后都能生成唯一的MD5值
> * **SHA (Secure Hash Algorithm，安全散列算法）**，数字签名等密码学应用中重要的工具，安全性高于MD5
> * **HMAC (Hash Message Authentication Code)**，散列消息鉴别码，基于密钥的Hash算法的认证协议。用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。常用于接口签名验证。


## 4.代码结构
<img src="https://img-blog.csdnimg.cn/20210507105312554.png" width="50%" />


## 5.工具类

**TokenUtil.java**

```java
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
                .withClaim("userid",user.getId())
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
            map.put("userid", decodedJwt.getClaim("userid").asInt());
            map.put("username", decodedJwt.getClaim("username").asString());
            map.put("email", decodedJwt.getClaim("email").asString());
            map.put("expire", decodedJwt.getExpiresAt());
            return map;
        } else {
            throw new RuntimeException("Token验证失败，请重新登录");
        }
    }
}
```



## 6.测试

> **测试地址1：** http://localhost:8081/index/login

**请求方式：POST**

**响应结果：**

<img src="https://img-blog.csdnimg.cn/202105061651022.png" width="100%" />




> **测试地址2：** http://localhost:8081/index/getInfo

**请求方式：GET**

**响应结果：**

<img src="https://img-blog.csdnimg.cn/20210506165009390.png" width="60%" />




## 7.源码地址

**Github：**[https://github.com/ACGkaka/SpringBootExamples/tree/main/springboot-jwt](https://github.com/ACGkaka/SpringBootExamples/tree/main/springboot-jwt)
