package com.timeless.sell.repository;

import com.timeless.sell.SellApplicationTests;
import com.timeless.sell.entity.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProductInfoRepositoryTest extends SellApplicationTests {
    
    @Autowired
    private ProductInfoRepository repository;
    
    @Test
    public void save(){
        ProductInfo productInfo = repository.save(ProductInfo.builder()
                .productId("123456")
                .productName("皮蛋粥")
                .productPrice(new BigDecimal(3.2))
                .productStock(100)
                .productDescription("好喝")
                .productIcon("xxx.jpg")
                .productStatus(0)
                .categoryType(3)
                .build());
        System.out.println(productInfo.toString());
        assertNotNull(productInfo);
    }
    
    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfos = repository.findByProductStatus(0);
        assertNotEquals(0, productInfos.size());
    }
}