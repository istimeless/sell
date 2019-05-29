package com.timeless.sell.converter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.entity.OrderDetail;
import com.timeless.sell.form.CartForm;
import com.timeless.sell.form.OrderForm;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijiayin
 */
public class OrderForm2OrderDTOConverter {
    
    public static OrderDTO convert(OrderForm orderForm){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        JSONArray items = JSONArray.parseArray(orderForm.getItems());
        for (int i = 0; i < items.size(); i++) {
            JSONObject item = items.getJSONObject(i);
            orderDetailList.add(OrderDetail.builder()
                    .productId(item.getString("productId"))
                    .productQuantity(item.getInteger("productQuantity"))
                    .build());
        }

        return OrderDTO.builder()
                    .buyerName(orderForm.getName())
                    .buyerPhone(orderForm.getPhone())
                    .buyerAddress(orderForm.getAddress())
                    .buyerOpenid(orderForm.getOpenid())
                    .orderDetailList(orderDetailList)
                    .build();
    }
}
