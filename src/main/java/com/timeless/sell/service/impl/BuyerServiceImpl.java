package com.timeless.sell.service.impl;

import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.exception.SellException;
import com.timeless.sell.service.BuyerService;
import com.timeless.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijiayin
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    
    @Autowired
    private OrderService orderService;
    
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到该订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        
        return orderService.cancel(orderDTO);
    }
    
    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        if(!StringUtils.equals(openid, orderDTO.getBuyerOpenid())){
            log.error("【查询订单详情】订单的openid不一致，orderId={}。 orderDTO={}",
                    orderId, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
