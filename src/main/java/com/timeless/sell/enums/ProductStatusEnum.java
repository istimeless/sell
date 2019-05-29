package com.timeless.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 * @author lijiayin
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum<Integer>{

    /**
     * 上架
     */
    UP(0, "上架"),

    /**
     * 下架
     */
    DOWN(1, "下架")
    ;
    
    private Integer code;
    
    private String message;
}
