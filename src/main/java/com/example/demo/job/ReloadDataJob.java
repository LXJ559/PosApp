package com.example.demo.job;


import com.example.demo.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReloadDataJob {

    private static Logger log = LoggerFactory.getLogger(ReloadDataJob.class);

    @Resource
    GoodsService goodsService;

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Scheduled(cron = "59 59 23 * * MON-FRI")
    public void execute(){
        log.info("start-->ReloadData Job");

        log.info("step 1-->empty data ");
        redisTemplate.opsForValue().set("goods::1",null);

        log.info("step 2-->reload data");
        goodsService.findAllGoods();

        log.info("finish-->ReloadData Job");
    }
}
