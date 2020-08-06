package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Goods;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/findAllGoods")
    public JSONObject findAllGoods(){
        List<Goods> goodsList = goodsService.findAllGoods();
        jsonObject.put("goodsList",goodsList);
        return jsonObject;
    }
}
