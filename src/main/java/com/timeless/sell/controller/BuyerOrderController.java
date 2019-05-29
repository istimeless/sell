package com.timeless.sell.controller;

import com.timeless.sell.annotation.AopLog;
import com.timeless.sell.converter.OrderForm2OrderDTOConverter;
import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.exception.SellException;
import com.timeless.sell.form.OrderForm;
import com.timeless.sell.service.BuyerService;
import com.timeless.sell.service.OrderService;
import com.timeless.sell.utils.ResultVOUtil;
import com.timeless.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, 
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确， orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), 
                    bindingResult.getFieldError().getDefaultMessage());
        }
        
        OrderDTO orderDTO = orderService.create(OrderForm2OrderDTOConverter.convert(orderForm));
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空， orderForm={}", orderForm);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        
        Map<String, String> map = new HashMap<>(2);
        map.put("orderId", orderDTO.getOrderId());
        
        return ResultVOUtil.success(map);
    }

    /**
     * 查询订单列表
     * @param openid
     * @param pageable
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid, 
                                         @PageableDefault() Pageable pageable){
        
        if(StringUtils.isBlank(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageable);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        if(StringUtils.isBlank(openid)){
            log.error("【查询订单详情】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if(StringUtils.isBlank(orderId)){
            log.error("【查询订单详情】orderId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @PutMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        if(StringUtils.isBlank(openid)){
            log.error("【取消订单】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if(StringUtils.isBlank(orderId)){
            log.error("【取消订单】orderId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        
        buyerService.cancelOrder(openid, orderId);
        
        return ResultVOUtil.success();
    }
}
