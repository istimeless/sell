package com.timeless.sell.service.impl;

import com.timeless.sell.config.WeChatAccountConfig;
import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author lijiayin
 */
@Slf4j
@Service
public class PushMessageServiceImpl implements PushMessageService {
    
    @Autowired
    private WxMpService wxMpService;
    
    @Autowired
    private WeChatAccountConfig accountConfig;
    
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser("");
        List<WxMpTemplateData> data = Arrays.asList(
            new WxMpTemplateData("first", "亲，记得收货"),
            new WxMpTemplateData("keyword1", "微信点餐")      
        );
        templateMessage.setData(data);
        try {
            String s = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}", e.getMessage(), e);
        }
    }
}
