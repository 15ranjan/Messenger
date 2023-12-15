package com.rio.demo.service;

import com.rio.demo.DTO.*;
import com.rio.demo.model.Chat;
import com.rio.demo.model.Message;
import com.rio.demo.model.User;
import com.rio.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public ResponseEntity<CreateUserResponse> createUser(User user) {
        Optional<User> present= userRepository.findByUsername(user.getUsername());
        CreateUserResponse createUserResponse;
        if(present.isPresent()){
            createUserResponse = new CreateUserResponse("failure", "User already exists");
            return new ResponseEntity<>(createUserResponse, HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user.clone());
        createUserResponse = new CreateUserResponse("success", "User Successfully Created");
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<GetUserResponse> getUsers() {
        List<String> usernames = userRepository.getAllUSerNames();
        return new ResponseEntity<>(new GetUserResponse("success", usernames), HttpStatus.OK);
    }


    public ResponseEntity<Response> sendMessage(String fromUsername, Message message) {
        Optional<User> optionalFromUser = userRepository.findByUsername(fromUsername);
        if(optionalFromUser.isPresent()){
            message.setFrom(fromUsername);
            //add message in user chats
            userRepository.addMessage(message);
            return new ResponseEntity<>(new Response("success"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Response("failure"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ChatDTO> getUnreadMessages(String fromUsername) {
        HashMap<String, Chat> chats = userRepository.getUnreadMessages(fromUsername);
        boolean newMessageFound = false;
        for(String key: chats.keySet()){
            if(!chats.get(key).getMessages().isEmpty()){
                newMessageFound = true;
                break;
            }
        }
        String message = newMessageFound ? "You have message(s)" : "No new messages";
        ChatDTO chatDTO = new ChatDTO("success", message, chats);
        return new ResponseEntity<>(chatDTO, HttpStatus.OK);
    }


    public ResponseEntity<ChatDTO> getChatHistoryWithUser(String fromUsername, String friend) {
        HashMap<String, Chat> chats = userRepository.getChatHistoryWithFriend(fromUsername, friend);
        return new ResponseEntity<>(new ChatDTO("success", null,chats), HttpStatus.OK);
    }

    public ResponseEntity<AllChatHistoryDTO> getAllChatHistory(String fromUsername) {
        HashMap<String, Chat> chats = userRepository.getChatHistory(fromUsername);
        return new ResponseEntity<>(new AllChatHistoryDTO("success", chats), HttpStatus.OK);
    }
}
