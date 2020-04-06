package com.micro.pmo.commons.utils;


import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 用于生成、验证和解析jwt
 */
public class JwtUtils {

    /**生成令牌时用的加密的盐*/
    public static final String KEY = "car2019";

    /**令牌颁发者*/
    public static final String PMO = "LC-car";

    /**过期时间：90天*/
    public static long EXPIRATION_MILLIS = 1000*60*60*24*90;

    /**
     *@描述 : 生成jwt
     *@参数 [ttlMillis, user]
     *@返回值 java.lang.String
     *@创建人 raoBo
     *@创建时间 2019.6.26
     *@修改人和其它信息
     */
    public static String createJWT(long ttlMillis, Map<String, Object> claims) {

        //指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        //Map<String, Object> claims = new HashMap<String, Object>();
        //claims.put("id", user.getId());
        //claims.put("username", user.getName());

        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                                 //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                                 .setClaims(claims)
                                 //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                                 .setId(UUID.randomUUID().toString())
                                 //iat: jwt的签发时间
                                 .setIssuedAt(now)
                                 .setAudience("big-screen")
                                 //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                                 .setSubject(PMO)
                                 //设置签名使用的签名算法和签名使用的秘钥
                                 .signWith(signatureAlgorithm, KEY);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     *@描述 :解析jwt
     *@参数 [token]
     *@返回值 io.jsonwebtoken.Claims
     *@创建人 raoBo
     *@创建时间 2019.6.26
     *@修改人和其它信息
     */
    public static Claims parseJWT(String token) {

        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(KEY)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

}

