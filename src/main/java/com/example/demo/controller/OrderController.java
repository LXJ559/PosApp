package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Goods;
import com.example.demo.entity.SysOrder;
import com.example.demo.entity.User;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2020-05-16 08:42:32
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    private SysOrder order = new SysOrder();

    private JSONObject jsonObject = new JSONObject();

    @PostMapping("/checkout")
    public JSONObject check(HttpServletRequest request, @RequestBody List<Map> paramList){
        String token = request.getHeader("Authorization");
        String username =  TokenUtil.tokenDepart(token);
//        System.out.println(username);
        for(Map param : paramList){
            //根据名字查找用户信息
            User orderUser = userService.findByName(username);
            //根据商品名查找商品信息
            Goods orderGoods = goodsService.findGoodsByName((String) param.get("goodsName"));
            //根据用户名和商品名查找订单，若重复，加数量
            SysOrder oldOrder = orderService.findOrder(orderUser.getId(),orderGoods.getId());
            if (!StringUtils.isEmpty(oldOrder)){
                oldOrder.setCount(oldOrder.getCount()+(Integer) param.get("count"));
                SysOrder result = orderService.addOrder(oldOrder);
                if(!StringUtils.isEmpty(result)){
                    jsonObject.put("status","success");
                }
            }else {
                order.setId(UUID.randomUUID().toString().replaceAll("-",""));
                order.setUserId(orderUser.getId());
                order.setGoodsId(orderGoods.getId());
                order.setCount((Integer) param.get("count"));
                SysOrder orderResult = orderService.addOrder(order);
                if(!StringUtils.isEmpty(orderResult)){
                    jsonObject.put("status","success");
                }
            }
        }
        return jsonObject;
    }

}