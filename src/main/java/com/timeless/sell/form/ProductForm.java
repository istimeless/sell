package com.timeless.sell.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {

    
    private String productId;

    /**
     * 名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    /**
     * 单价
     */
    @NotNull(message = "商品单价不能为空")
    private BigDecimal productPrice;

    /**
     * 库存
     */
    @NotNull(message = "商品库存不能为空")
    private Integer productStock;

    /**
     * 描述
     */
    
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    @NotNull(message = "商品类目不能为空")
    private Integer categoryType;
}
