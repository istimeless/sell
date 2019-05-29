package com.timeless.sell.converter;

import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijiayin
 */
public class OrderMaster2OrderDtoConverter {
    
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map(OrderMaster2OrderDtoConverter::convert)
               .collect(Collectors.toList());
    }
}
