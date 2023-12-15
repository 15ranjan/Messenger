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

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        return String.format("Hello %s", name);
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<GetUserResponse> getUsers(@RequestHeader("username") String username, @RequestHeader("apiKey") String apiKey){
        return userService.getUsers(username, apiKey);
    }

    @PostMapping("/user/{fromUsername}/message")
    public ResponseEntity<Response> sendMessage(@PathVariable String fromUsername, @RequestBody Message message, @RequestHeader("apiKey") String apiKey){
        return userService.sendMessage(fromUsername, message, apiKey);
    }

    @GetMapping("/user/{fromUsername}/message")
    public ResponseEntity<ChatDTO> getUnreadMessages(@PathVariable String fromUsername, @RequestParam(required = false) String friend, @RequestHeader("apiKey") String apiKey){
        if(Objects.nonNull(friend)){
            return userService.getChatHistoryWithUser(fromUsername, friend, apiKey);
        }
        return userService.getUnreadMessages(fromUsername, apiKey);
    }

    @GetMapping("/user/{fromUsername}/message/all")
    public ResponseEntity<AllChatHistoryDTO> getAllChatHistory(@PathVariable String fromUsername, @RequestHeader("apiKey") String apiKey){
        return userService.getAllChatHistory(fromUsername, apiKey);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.login(userRequestDTO);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Response> logout(@RequestBody() UserRequestDTO userRequestDTO) {
        return userService.logout(userRequestDTO);
    }

}
