package com.rio.demo.DTO;

import com.rio.demo.model.Chat;

import java.util.HashMap;

public class AllChatHistoryDTO extends Response{
    HashMap<String, Chat> chats;

    public AllChatHistoryDTO(String status, HashMap<String, Chat> chats){
        super(status);
        this.chats = chats;
    }

    public HashMap<String, Chat> getChats() {
        return chats;
    }
}
