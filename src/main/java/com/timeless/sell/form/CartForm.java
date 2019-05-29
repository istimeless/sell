package com.timeless.sell.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartForm {

    /**
     * 商品id
     */
    @NotEmpty(message = "商品id必填")
    private String productId;

    /**
     * 商品数量
     */
    @NotEmpty(message = "商品数量必填")
    private Integer productQuantity;
}
