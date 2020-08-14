package com.example.demo.service.impl;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("MessageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

}
