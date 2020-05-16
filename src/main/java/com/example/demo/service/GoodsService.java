package com.example.demo.service;

import com.example.demo.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods addGoods(Goods goods);

    Goods findGoodsByName(String goodsName);

    List<Goods> findAllGoods();
}
