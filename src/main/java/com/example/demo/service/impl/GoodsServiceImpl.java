package com.example.demo.service.impl;

import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsRepository goodsRepository;

    //注意一定要加key,否则无法清除缓存
    @Override
    @CacheEvict(cacheNames = "goods",key = "1")
    public Goods addGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods findGoodsByName(String goodsName) {
        return goodsRepository.findByName(goodsName);
    }

    @Override
    @Cacheable(cacheNames = "goods",key = "1")
    public List<Goods> findAllGoods() {
        System.out.println("执行了");
        return goodsRepository.findAll();
    }

    @Override
    public Goods findGoodsById(String goodsId) {
        return goodsRepository.findGoodsById(goodsId);
    }

    @Override
    public Goods findGoodsByGoodsId(Integer goodsId) {
        return goodsRepository.findGoodsByGoodsId(goodsId);
    }

    @Override
    @CacheEvict(cacheNames = "goods",key = "1")
    public void updateGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    @CacheEvict(cacheNames = "goods",key = "1")
    public void deleteGoods(Integer goodsId) {
        goodsRepository.deleteGoodsByGoodsId(goodsId);
    }

}
