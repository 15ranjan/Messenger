package com.rio.demo.repository;

import com.rio.demo.model.Chat;
import com.rio.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Component
public class UserRepo {
    private final static HashMap<String, User> users = new HashMap<>();

    public boolean userExists(String username){
        return users.containsKey(username);
    }
    public void save(User user){
        users.put(user.getUsername(), user);
    }

    public Optional<User> findByUsername(String username){
        User user = users.get(username);
        return Optional.ofNullable(user == null ? null : user.clone());
    }

    public void addChat(String fromUsername, Chat chat){
        HashMap<String, Chat> chats = users.get(fromUsername).getChats();
        if(chats.containsKey(chat.getUsername())){
            chats.get(chat.getUsername()).getMessages().addAll(chat.getMessages());
        }
        else{
            chats.put(chat.getUsername(), chat);
        }
    }

    public List<String> getAllUSerNames() {
        return new ArrayList<>(users.keySet());
    }
}
