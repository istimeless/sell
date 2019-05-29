package com.timeless.sell.controller;

import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.exception.SellException;
import com.timeless.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author lijiayin
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/list")
    public ModelAndView list(@PageableDefault Pageable pageable){
        Page<OrderDTO> orderDTOPage = orderService.findList(pageable);
        return new ModelAndView("order/list", "orderDTOPage", orderDTOPage);
    }
    
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, 
                               Map<String, Object> map){
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家订单取消】发生异常：{}", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家查询订单详情】发生异常：{}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("order/detail", "orderDTO", orderDTO);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家订单完结】发生异常：{}", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }
}
