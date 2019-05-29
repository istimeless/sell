package com.timeless.sell.service.impl;

import com.timeless.sell.entity.SellerInfo;
import com.timeless.sell.repository.SellerInfoRepository;
import com.timeless.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijiayin
 */
@Service
public class SellerServiceImpl implements SellerService {
    
    @Autowired
    private SellerInfoRepository repository;
    
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
