package com.timeless.sell.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 类目名称
     */
    @NotBlank
    private String  categoryName;

    /**
     * 类目类型
     */
    @NotNull
    private Integer categoryType;
}
