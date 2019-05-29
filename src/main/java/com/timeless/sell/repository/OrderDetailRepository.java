package com.timeless.sell.repository;

import com.timeless.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lijiayin
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据orderId查找订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
