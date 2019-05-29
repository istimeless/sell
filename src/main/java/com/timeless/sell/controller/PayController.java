package com.timeless.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.exception.SellException;
import com.timeless.sell.service.OrderService;
import com.timeless.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijiayin
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PayService payService;
    
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        
        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        Map<String, Object> param = new HashMap<>(16);
        param.put("payResponse", payResponse);
        param.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", param);
    }
    
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
