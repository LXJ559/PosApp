package com.example.demo.service;

import com.example.demo.entity.MessageVO;

import java.util.List;

public interface MessageService {

    void addMessage(MessageVO message);

    List<MessageVO> getMessages();
}
