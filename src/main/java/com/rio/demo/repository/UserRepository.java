package com.rio.demo.repository;

import com.rio.demo.model.Chat;
import com.rio.demo.model.Message;
import com.rio.demo.model.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;


@Component
public class UserRepository {
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

    public void addMessage(Message message){
        String fromUsername = message.getFrom();
        String toUsername = message.getTo();
        User toUser = users.get(toUsername);
        User fromUser = users.get(fromUsername);
        
        //add message to fromUser
        addMessageToUserChatHistory(message, fromUser.getChats(), toUsername);
        //add message to toUser
        addMessageToUserChatHistory(message, toUser.getChats(), fromUsername);
    }

    private static void addMessageToUserChatHistory(Message message, HashMap<String, Chat> chats, String toUsername) {
        message.setSentAt(new Timestamp(System.currentTimeMillis()));
        if(chats.containsKey(toUsername)){
            Chat userChat = chats.get(toUsername);
            List<Message> messages = userChat.getMessages();
            messages.add(message);
        }
        else{
            List<Message> temp = new ArrayList<>();
            temp.add(message);
            Chat chat = new Chat();
            chat.setUsername(toUsername);
            chat.setMessages(temp);
            chats.put(toUsername, chat);
        }
    }

    public HashMap<String, Chat> getUnreadMessages(String fromUsername){
        HashMap<String, Chat> chats = users.get(fromUsername).getChats();
        HashMap<String, Chat> unreadChats = new HashMap<>();
        for(String key: chats.keySet()){
            Chat userChat = chats.get(key).clone();
            removeSentMessagesFrom(userChat, fromUsername);
            removeDeliveredMessages(userChat);
            unreadChats.put(key, userChat);
        }
        return unreadChats;
    }

    private void removeSentMessagesFrom(Chat userChat, String fromUsername) {
        List<Message> messages = userChat.getMessages();
        messages.removeIf(message -> message.getFrom().equals(fromUsername));
    }

    private void removeDeliveredMessages(Chat userChat) {
        List<Message> messages = userChat.getMessages();
        messages.removeIf(message -> Objects.nonNull(message.getDeliveredAt()));
    }

    public HashMap<String, Chat> getChatHistory(String fromUsername){
        return users.get(fromUsername).getChats();
    }
    public HashMap<String, Chat> getChatHistoryWithFriend(String fromUsername, String toUsername){
        HashMap<String, Chat> chats = users.get(fromUsername).getChats();
        chats.entrySet().removeIf(chat -> !chat.getKey().equals(toUsername));
        return chats;
    }

    public List<String> getAllUSerNames() {
        return new ArrayList<>(users.keySet());
    }
}
