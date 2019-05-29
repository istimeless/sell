package com.timeless.sell.service;

import com.timeless.sell.entity.SellerInfo;

/**
 * @author lijiayin
 */
public interface SellerService {

    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
