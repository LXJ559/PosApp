package com.example.demo.service.impl;

import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods addGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods findGoodsByName(String goodsName) {
        return goodsRepository.findByName(goodsName);
    }

    @Override
    @Cacheable(cacheNames = "goods")
    public List<Goods> findAllGoods() {
        System.out.println("执行了");
        return goodsRepository.findAll();
    }

    @Override
    public Goods findGoodsById(String goodsId) {
        return goodsRepository.findGoodsById(goodsId);
    }

}
