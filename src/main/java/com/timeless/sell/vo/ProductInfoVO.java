package com.timeless.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品详情
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoVO {
    
    @JsonProperty("id")
    private String productId;

    /**
     * 名称
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 单价
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 小图
     */
    @JsonProperty("icon")
    private String productIcon;
    
}
