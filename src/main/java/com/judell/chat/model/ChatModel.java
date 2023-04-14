package com.judell.chat.model;

import com.judell.chat.constants.MessageType;
import lombok.Data;

@Data
public class ChatModel {
    private String content;
    private String sender;
    private String receiver;
    private MessageType type;
}
