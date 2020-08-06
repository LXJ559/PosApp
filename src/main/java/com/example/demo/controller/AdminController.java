package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("")
    public String index(){
        return "index";
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
