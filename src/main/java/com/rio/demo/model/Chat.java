package com.rio.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String username;
    private List<Message> messages;

    public Chat() {
        this.messages = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Chat clone(){
        Chat chat = new Chat();
        chat.setUsername(this.getUsername());
        List<Message> temp = new ArrayList<>();
        for(Message message: this.getMessages()){
            temp.add(message.clone());
        }
        chat.setMessages(temp);
        return chat;
    }
}
