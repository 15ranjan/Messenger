package com.rio.demo.DTO;

import com.rio.demo.model.Chat;

import java.util.HashMap;

public class ChatDTO extends Response{
    private String message;
    private HashMap<String, Chat> chats;

    public ChatDTO(String status, String message, HashMap<String, Chat> chats){
        super(status);
        this.message = message;
        this.chats = chats;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, Chat> getChats() {
        return chats;
    }
}
