package com.timeless.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author lijiayin
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    /**
     * 公众平台id
     */
    private String mpAppId;

    /**
     * 公众平台密钥
     */
    private String mpAppSecret;

    /**
     * 开发平台id
     */
    private String openAppId;

    /**
     * 开放平台密钥
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信公众平台授权url
     */
    private String weChatMpAuthorize;

    /**
     * 微信开发平台授权url
     */
    private String weChatOpenAuthorize;

    /**
     * 微信模板id
     */
    private Map<String, String> templateId;
}
