package com.example.demo.service;

import com.example.demo.entity.SysOrder;

/**
 * (Order)表服务接口
 * @author makejava
 * @since 2020-05-16 08:42:32
 */
public interface OrderService {

    SysOrder findOrder(String userId, String goodsId);

    SysOrder addOrder(SysOrder oldOrder);
}