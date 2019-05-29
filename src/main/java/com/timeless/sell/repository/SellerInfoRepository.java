package com.timeless.sell.repository;

import com.timeless.sell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lijiayin
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    
    SellerInfo findByOpenid(String openid);
}
