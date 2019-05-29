package com.timeless.sell.repository;

import com.timeless.sell.SellApplicationTests;
import com.timeless.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryRepositoryTest extends SellApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    @Test
    public void findOne(){
        ProductCategory productCategory = productCategoryRepository.findById(1).orElse(null);
        System.out.println(productCategory.toString());
    }
    
    @Test
    @Transactional
    public void save(){
        productCategoryRepository.save(ProductCategory.builder()
                .categoryName("女生最爱").categoryType(2).build());
    }

    @Test
    public void update(){
        ProductCategory productCategory = productCategoryRepository.findById(1).orElse(null);
        productCategory.setCategoryType(2);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn(){
        List<ProductCategory> categories = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(2, 3));
        Assert.assertNotEquals(0, categories.size());
    }
}