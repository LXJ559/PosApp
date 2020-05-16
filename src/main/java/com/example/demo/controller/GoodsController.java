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

    private Goods goods = new Goods();

    private JSONObject jsonObject = new JSONObject();

    @GetMapping("/findAllGoods")
    public JSONObject findAllGoods(){
        List<Goods> goodsList = goodsService.findAllGoods();
        jsonObject.put("goodsList",goodsList);
        return jsonObject;
    }


//    增加商品，因为没有写admin端，所以暂时用这个填数据
//    @PostMapping("/checkout")
//    public JSONObject check(@RequestBody List<Map> paramList){
//        for (Map param:paramList) {
//            //如果已经存在，则只增加数量
//            Goods oldGoods = goodsService.findGoods((String) param.get("goodsName"));
//            if (!StringUtils.isEmpty(oldGoods)){
//                oldGoods.setGoodsCount(oldGoods.getGoodsCount()+1000);
//                Goods result = goodsService.addGoods(oldGoods);
//                if(!StringUtils.isEmpty(result)){
//                    jsonObject.put("status","success");
//                }
//            }else {
//                //不存在，就直接整个加在数据库
//                System.out.println(param.toString());
//                goods.setId(UUID.randomUUID().toString().replaceAll("-",""));
//                goods.setGoodsId((Integer)param.get("goodsId"));
//                goods.setGoodsName((String) param.get("goodsName"));
//                goods.setPrice((Integer) param.get("price"));
//                goods.setGoodsCount(1000);
//                Goods goodsResult = goodsService.addGoods(goods);
//                if (StringUtils.isEmpty(goodsResult)){
//                    jsonObject.put("status","fail");
//                }
//            }
//        }
//        return jsonObject;
//    }
}
