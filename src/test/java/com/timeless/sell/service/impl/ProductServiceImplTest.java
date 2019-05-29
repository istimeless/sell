package com.timeless.sell.service.impl;

import com.timeless.sell.SellApplicationTests;
import com.timeless.sell.entity.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends SellApplicationTests {

    @Autowired
    private ProductServiceImpl productService;
    
    @Test
    public void findOne() {
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 10);
        Page<ProductInfo> productInfos = productService.findAll(request);
        productInfos.get().forEach(productInfo -> System.out.println(productInfo.toString()));
    }

    @Test
    public void save() {
    }
}