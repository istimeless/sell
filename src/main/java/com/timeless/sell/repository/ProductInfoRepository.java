package com.timeless.sell.repository;

import com.timeless.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lijiayin
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
