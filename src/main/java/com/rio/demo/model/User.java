package com.rio.demo.model;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String token;
    private Timestamp validTill;
    private HashMap<String, Chat> chats;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.chats = new HashMap<>();
    }

    public User() {
        this.chats = new HashMap<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setChats(HashMap<String, Chat> chats) {
        this.chats = chats;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getValidTill() {
        return validTill;
    }

    public void setValidTill(Timestamp validTill) {
        this.validTill = validTill;
    }

    public HashMap<String, Chat> getChats() {
        return chats;
    }

    public User clone(){
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setToken(this.getToken());
        user.setValidTill(this.validTill);
        if(Objects.nonNull(this.chats)){
            for (Map.Entry<String, Chat> eachChat : this.chats.entrySet()) {
                user.chats.put(eachChat.getKey(), eachChat.getValue().clone());
            }
        }
        return user;
    }
}
