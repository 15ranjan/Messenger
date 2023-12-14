package com.rio.demo.model;


import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String token;
    private HashMap<String, Chat> chats;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
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

    public HashMap<String, Chat> getChats() {
        return chats;
    }

    public User clone(){
        User user = new User();
        user.setUsername(this.username);
        user.setChats(this.getChats());
        user.setPassword(this.password);
        user.setToken(this.getToken());
        return user;
    }
}
