package com.timeless.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
public class WeixinController {
    
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbcbfb086efef5f09&secret=b560b1b193b32d61f7baa0aa2eece1d1&code="+ code +"&grant_type=authorization_code";
        RestTemplate template = new RestTemplate();
        String response = template.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
