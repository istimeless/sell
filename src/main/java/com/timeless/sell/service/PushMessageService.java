package com.timeless.sell.service;

import com.timeless.sell.dto.OrderDTO;

/**
 * @author lijiayin
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
