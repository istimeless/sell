package com.timeless.sell.repository;

import com.timeless.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lijiayin
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
