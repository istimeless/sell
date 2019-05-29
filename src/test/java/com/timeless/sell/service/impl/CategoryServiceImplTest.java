package com.timeless.sell.service.impl;

import com.timeless.sell.SellApplicationTests;
import com.timeless.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CategoryServiceImplTest extends SellApplicationTests {

    @Autowired
    private CategoryServiceImpl categoryService;
    
    @Test
    public void findOne() {
        ProductCategory category = categoryService.findOne(1);
        assertNotNull(category);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByCategoryTypeIn() {
    }

    @Test
    public void save() {
    }
}