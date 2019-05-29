package com.timeless.sell.aspect;

import com.timeless.sell.constant.CookieConstant;
import com.timeless.sell.constant.RedisConstant;
import com.timeless.sell.exception.SellAuthorizeException;
import com.timeless.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayin
 */
@Slf4j
@Aspect
@Component
public class SellerAuthorizeAspect {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Pointcut("execution(public * com.timeless.sell.controller.Seller*.*(..))" + 
    "&& !execution(public * com.timeless.sell.controller.SellerUserController.*(..))")
    public void verify() {}
    
    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
//        //查询cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if(cookie == null){
//            log.warn("【登录校验】cookie中查询不到token");
//            throw new SellAuthorizeException();
//        }
//        
//        //去redis查询
//        String tokenValue = stringRedisTemplate.opsForValue().get(
//                String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if(StringUtils.isBlank(tokenValue)){
//            log.warn("【登录校验】redis中查询不到token");
//            throw new SellAuthorizeException();
//        }
    }
}
