package com.example.demo.service.impl;

import com.example.demo.entity.SysOrder;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2020-05-16 08:42:32
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public SysOrder findOrder(String userId, String goodsId) {
        return orderRepository.findByUidAndGid(userId,goodsId);
    }

    @Override
    public SysOrder addOrder(SysOrder oldOrder) {
        return orderRepository.save(oldOrder);
    }

    @Override
    public List<SysOrder> findCurrentUserOrder(String userId) {
        return orderRepository.findOrderByUserId(userId);
    }


}