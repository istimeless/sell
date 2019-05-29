package com.timeless.sell.service.impl;

import com.timeless.sell.SellApplicationTests;
import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class OrderServiceImplTest extends SellApplicationTests {

    @Autowired
    private OrderServiceImpl orderService;
    
    private final String BUYER_OPENID = "110110";
    
    @Test
    public void create() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        
        orderDetailList.add(OrderDetail.builder()
                .productId("123458")
                .productQuantity(1)
                .build());
        orderDetailList.add(OrderDetail.builder()
                .productId("123457")
                .productQuantity(2)
                .build());
        
        OrderDTO result = orderService.create(OrderDTO.builder()
                .buyerAddress("北京市")
                .buyerName("ABC")
                .buyerPhone("123456789012")
                .buyerOpenid(BUYER_OPENID)
                .orderDetailList(orderDetailList)
                .build());
        log.info("【创建订单】 result={}", result.toString());
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1558600606466183696");
        log.info(orderDTO.toString());
        assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}