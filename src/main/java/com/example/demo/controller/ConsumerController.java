package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.MessageVO;
import com.example.demo.service.MessageService;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;


@Controller
public class ConsumerController {

    @Resource
    private WebSocketServerEndpoint WebSocketServerEndpoint;

    private MessageVO messageVO = new MessageVO();

    @Resource
    private MessageService messageService;

    private JSONObject jsonObject = new JSONObject();



    @RabbitListener(queues = "pos.news")
    public void receive(Message message){

        try {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            JSONObject jsonMsg = JSON.parseObject(msg);
            messageVO.setId(UUID.randomUUID().toString().replaceAll("-",""));
            messageVO.setTitle(jsonMsg.getString("msgTitle"));
            messageVO.setType(jsonMsg.getString("msgType"));
            messageVO.setContent(jsonMsg.getString("msgContent"));
            messageService.addMessage(messageVO);
//            System.out.println(j.getString("msgTitle") + ";" + j.getString("msgType") + ";" + j.getString("msgContent"));

//            WebSocketServerEndpoint.sendMessageToAll(msg);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ResponseBody
    @GetMapping("/user/getMessages")
    public  JSONObject getMessages(){
        List<MessageVO> messageVOList = messageService.getMessages();
        jsonObject.put("messageList",messageVOList);
        return jsonObject;
    }


}
