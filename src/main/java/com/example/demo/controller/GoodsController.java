package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {
    @RequestMapping("/checkout")
    public JSONObject check(@RequestBody List<Map> paramList){
        for (Map param:paramList) {
            System.out.println(param.toString());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }
}
