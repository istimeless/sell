package com.timeless.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;
}
