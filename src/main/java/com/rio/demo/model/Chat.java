package com.rio.demo.model;

import java.util.List;

public class Chat {
    private String username;
    private List<Message> messages;

    public Chat() {
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
        chat.setMessages(this.getMessages());
        chat.setUsername(this.getUsername());
        return chat;
    }
}
