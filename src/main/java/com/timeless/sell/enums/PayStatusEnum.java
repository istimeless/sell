package com.timeless.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lijiayin
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements CodeEnum<Integer> {

    /**
     * 等待支付
     */
    WAIT(0, "等待支付"),

    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功")
    ;
    
    private Integer code;
    
    private String message;
}
