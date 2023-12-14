package com.rio.demo.controller;


import com.rio.demo.DTO.CreateUserResponse;
import com.rio.demo.DTO.GetUserResponse;
import com.rio.demo.model.User;
import com.rio.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
