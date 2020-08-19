package com.example.demo.service;

import com.example.demo.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoodsService {

    Goods addGoods(Goods goods);

    Goods findGoodsByName(String goodsName);

    List<Goods> findAllGoods();

    Goods findGoodsById(String goodsId);

    Goods findGoodsByGoodsId(Integer goodsId);

    void updateGoods(Goods goods);

    void deleteGoods(Integer goodsId);

}
