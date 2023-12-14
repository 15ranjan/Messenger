package com.rio.demo.service;

import com.rio.demo.DTO.CreateUserResponse;
import com.rio.demo.DTO.GetUserResponse;
import com.rio.demo.model.User;
import com.rio.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    public ResponseEntity<CreateUserResponse> createUser(User user) {
        Optional<User> present=userRepo.findByUsername(user.getUsername());
        CreateUserResponse createUserResponse;
        if(present.isPresent()){
            createUserResponse = new CreateUserResponse("failure", "User already exists");
            return new ResponseEntity<>(createUserResponse, HttpStatus.BAD_REQUEST);
        }
        userRepo.save(user.clone());
        createUserResponse = new CreateUserResponse("success", "User Successfully Created");
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<GetUserResponse> getUsers() {
        List<String> usernames = userRepo.getAllUSerNames();
        return new ResponseEntity<>(new GetUserResponse("success", usernames), HttpStatus.OK);
    }
}
