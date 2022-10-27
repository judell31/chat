package com.judell.chat.controller;

import com.judell.chat.model.ChatModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatModel register(@Payload ChatModel chatModel, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatModel.getSender());
        return chatModel;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatModel sendMessage(@Payload ChatModel chatModel) {
        return chatModel;
    }
}
