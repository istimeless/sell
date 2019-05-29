package com.timeless.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lijiayin
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements CodeEnum<Integer> {

    /**
     * 新订单
     */
    NEW(0, "新订单"),

    /**
     * 完结
     */
    FINISH(1, "完结"),

    /**
     * 已取消
     */
    CANCEL(2, "已取消")
    ;
    
    private Integer code;
    
    private String message;
}
