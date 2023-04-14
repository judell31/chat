package com.judell.chat.controller;

import com.judell.chat.model.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatModel register(@Payload ChatModel chatModel, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatModel.getSender());

        log.info(chatModel.getSender() + " has connected");

        return chatModel;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatModel sendMessage(@Payload ChatModel chatModel) {
        simpMessagingTemplate.convertAndSendToUser(chatModel.getReceiver(), "/private", chatModel);

        return chatModel;
    }
}
