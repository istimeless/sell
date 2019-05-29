package com.timeless.sell.service;

import com.timeless.sell.entity.ProductCategory;

import java.util.List;

/**
 * @author lijiayin
 */
public interface CategoryService {
    
    ProductCategory findOne(Integer categoryId);
    
    List<ProductCategory> findAll();
    
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    
    ProductCategory save(ProductCategory category);
}
