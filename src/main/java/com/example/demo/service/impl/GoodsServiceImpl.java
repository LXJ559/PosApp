package com.example.demo.service.impl;

import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Goods> findAllGoods() {
        return goodsRepository.findAll();
    }

}
