package com.timeless.sell.controller;

import com.timeless.sell.config.ProjectConfig;
import com.timeless.sell.constant.CookieConstant;
import com.timeless.sell.constant.RedisConstant;
import com.timeless.sell.entity.SellerInfo;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.service.SellerService;
import com.timeless.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lijiayin
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    
    @Autowired
    private SellerService sellerService;
    
    @Autowired
    private ProjectConfig projectConfig;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map){
        //1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), 
                openid, expire, TimeUnit.SECONDS);
        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:" + projectConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map){
        //1.从Cookie查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null){
            //2.清除redis
            stringRedisTemplate.opsForValue().getOperations()
                    .delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //3.清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
