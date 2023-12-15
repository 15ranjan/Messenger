package com.rio.demo.controller;


import com.rio.demo.DTO.*;
import com.rio.demo.model.Message;
import com.rio.demo.model.User;
import com.rio.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{name}/hello")
    public String sayHello(@PathVariable String name){
        return String.format("Hello %s", name);
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<GetUserResponse> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user/{fromUsername}/message")
    public ResponseEntity<Response> sendMessage(@PathVariable String fromUsername, @RequestBody Message message){
        return userService.sendMessage(fromUsername, message);
    }

    @GetMapping("/user/{fromUsername}/message")
    public ResponseEntity<ChatDTO> getUnreadMessages(@PathVariable String fromUsername, @RequestParam(required = false) String friend){
        if(Objects.nonNull(friend)){
            return userService.getChatHistoryWithUser(fromUsername, friend);
        }
        return userService.getUnreadMessages(fromUsername);
    }

    @GetMapping("/user/{fromUsername}/message/all")
    public ResponseEntity<AllChatHistoryDTO> getAllChatHistory(@PathVariable String fromUsername){
        return userService.getAllChatHistory(fromUsername);
    }

}
