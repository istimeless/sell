package com.timeless.sell.service.impl;

import com.timeless.sell.entity.ProductCategory;
import com.timeless.sell.repository.ProductCategoryRepository;
import com.timeless.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijiayin
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private ProductCategoryRepository repository;
    
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return repository.save(category);
    }
}
