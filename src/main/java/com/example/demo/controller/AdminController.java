package com.example.demo.controller;


import com.example.demo.entity.Goods;
import com.example.demo.entity.MessageVO;
import com.example.demo.entity.MsgType;
import com.example.demo.entity.Page;
import com.example.demo.service.GoodsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private RabbitTemplate rabbitTemplate;


    //注意地址跳转不要加responsebody，否则会返回json字符串
    @RequestMapping("")
    public String index(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                        @RequestParam(value = "size", defaultValue = "5") int size){
        List<Goods> goodsList = goodsService.findAllGoods();
        goodsList.sort(new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return -Integer.compare(o1.getGoodsId(), o2.getGoodsId());
            }
        });
        //设置自己的分页，pageable存在序列化json，以及分页缓存的问题
        Page<Goods> page = new Page<>();
        page.setNumber(start);
        page.setTotalPages(goodsList.size() % size == 0 ? (goodsList.size() / size):(goodsList.size() / size)+1);
        page.setList(start>=page.getTotalPages()-1 ? goodsList.subList((page.getTotalPages()-1)*size,goodsList.size()) : goodsList.subList(start*size,start*size+size));
        model.addAttribute("goodsPage",page);
        model.addAttribute("start",start);
        return "index";
    }



    //增加商品
    @PostMapping("/addGoods")
    public String addGoods( Goods goods, Model model){
        //如果已经存在，则只增加数量
        Goods oldGoods = goodsService.findGoodsByName(goods.getGoodsName());
        if (!StringUtils.isEmpty(oldGoods)){
            oldGoods.setGoodsCount(oldGoods.getGoodsCount()+goods.getGoodsCount());
            Goods result = goodsService.addGoods(oldGoods);
            if(!StringUtils.isEmpty(result)){
                model.addAttribute("status","success");
            }
        }else {
            //不存在，就直接整个加在数据库
            System.out.println(goods.toString());
            goods.setId(UUID.randomUUID().toString().replaceAll("-",""));
            goods.setGoodsId(goods.getGoodsId());
            goods.setGoodsName(goods.getGoodsName());
            goods.setPrice(goods.getPrice());
            goods.setGoodsCount(goods.getGoodsCount());
            Goods goodsResult = goodsService.addGoods(goods);
            if (StringUtils.isEmpty(goodsResult)){
                model.addAttribute("status","fail");
            }
        }
        return "redirect:/admin";
    }

    //中英文切换带多个参数，用此法
    @GetMapping("/goods")
    public String getGoodsInfo(@RequestParam String goodsId,Model model){
        /*
         * 这个可用@RequestParam替代，@RequestParam接受的是？后面的参数名称，
         * 而@PathVariable接受的是进行占位的名称，如下面的方法
         */
        //int goodsId = Integer.parseInt(httpServletRequest.getParameter("goodsId"));
        Goods goods = goodsService.findGoodsByGoodsId(Integer.parseInt(goodsId));
        model.addAttribute("goods",goods);
        return "edit";
    }


    @GetMapping("/goods/{goodsId}")
    public String getGoodsInfo(@PathVariable("goodsId") Integer goodsId ,Model model){
        Goods goods = goodsService.findGoodsByGoodsId(goodsId);
        model.addAttribute("goods",goods);
        return "edit";
    }

    @PostMapping("/editGoods")
    public String updateGoods(Goods goods){
        goods.setId(goods.getId());
        goods.setGoodsId(goods.getGoodsId());
        goods.setGoodsName(goods.getGoodsName());
        goods.setPrice(goods.getPrice());
        goods.setGoodsCount(goods.getGoodsCount());
        goodsService.updateGoods(goods);
        return  "redirect:/admin";
    }

    @GetMapping("/delGoods/{goodsId}")
    public String deleteGoods(@PathVariable("goodsId") Integer goodsId){
        goodsService.deleteGoods(goodsId);
        return "redirect:/admin";
    }

    @PostMapping("/sendMsg")
    public String sendMsg(MessageVO message){
        Map<String,Object> map = new HashMap<>();
        map.put("msgTitle",message.getTitle());
        map.put("msgType",message.getType());
        map.put("msgContent",message.getContent());
        if (MsgType.FANOUT.equals(message.getType())) {
            rabbitTemplate.convertAndSend("pos.fanout","pos.*",map);
        }else {
            rabbitTemplate.convertAndSend("pos.topic","*.topic",map);
        }
        return "redirect:/admin";
    }
}
