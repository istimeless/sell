package com.timeless.sell.service;

import com.timeless.sell.dto.CartDTO;
import com.timeless.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lijiayin
 */
public interface ProductService {
    
    ProductInfo findOne(String productId);

    /**
     * 查询所有已上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();
    
    Page<ProductInfo> findAll(Pageable pageable);
    
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);
    
    ProductInfo onSale(String productId);

    ProductInfo offSale(String productId);
}
