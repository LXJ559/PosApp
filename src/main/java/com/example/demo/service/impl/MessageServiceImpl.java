package com.example.demo.service.impl;

import com.example.demo.entity.MessageVO;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("MessageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Override
    @CacheEvict(cacheNames = "messages",key = "1")
    public void addMessage(MessageVO message) {
        messageRepository.save(message);
    }

    @Override
    @Cacheable(cacheNames = "messages",key = "1")
    public List<MessageVO> getMessages() {
        return messageRepository.findAll();
    }

}
