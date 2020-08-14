package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


@Component
public class ConsumerController {

    @Autowired
    private WebSocketServerEndpoint WebSocketServerEndpoint;



    @RabbitListener(queues = "pos.news")
    public void receive(Message message){

        try {
//            String msg = Arrays.toString(message.getBody());
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//            JSONObject j = JSON.parseObject(msg);
//
//
//            System.out.println(j.getString("msgTitle") + ";" + j.getString("msgType") + ";" + j.getString("msgContent"));

            WebSocketServerEndpoint.sendMessageToAll(msg);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
