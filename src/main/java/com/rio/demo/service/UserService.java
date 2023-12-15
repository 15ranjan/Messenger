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

import java.sql.Timestamp;
import java.util.*;

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

    public ResponseEntity<GetUserResponse> getUsers(String username, String apiKey) {
        if(!isUserAuthenticated(username, apiKey)){
            return new ResponseEntity<>(new GetUserResponse("failure", null), HttpStatus.UNAUTHORIZED);
        }
        List<String> usernames = userRepository.getAllUSerNames();
        return new ResponseEntity<>(new GetUserResponse("success", usernames), HttpStatus.OK);
    }


    public ResponseEntity<Response> sendMessage(String fromUsername, Message message, String apiKey) {
        if(!isUserAuthenticated(fromUsername, apiKey)){
            return new ResponseEntity<>(new Response("failure"), HttpStatus.UNAUTHORIZED);
        }
        Optional<User> optionalFromUser = userRepository.findByUsername(fromUsername);
        message.setFrom(fromUsername);
        //add message in user chats
        userRepository.addMessage(message);
        return new ResponseEntity<>(new Response("success"), HttpStatus.CREATED);
    }

    public ResponseEntity<ChatDTO> getUnreadMessages(String fromUsername, String apiKey) {
        if(!isUserAuthenticated(fromUsername, apiKey)){
            return new ResponseEntity<>(new ChatDTO("failure", null, null), HttpStatus.UNAUTHORIZED);
        }
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


    public ResponseEntity<ChatDTO> getChatHistoryWithUser(String fromUsername, String friend, String apiKey) {
        if(!isUserAuthenticated(fromUsername, apiKey)){
            return new ResponseEntity<>(new ChatDTO("failure", null, null), HttpStatus.UNAUTHORIZED);
        }
        HashMap<String, Chat> chats = userRepository.getChatHistoryWithFriend(fromUsername, friend);
        return new ResponseEntity<>(new ChatDTO("success", null,chats), HttpStatus.OK);
    }

    public ResponseEntity<AllChatHistoryDTO> getAllChatHistory(String fromUsername, String apiKey) {
        if(!isUserAuthenticated(fromUsername, apiKey)){
            return new ResponseEntity<>(new AllChatHistoryDTO("failure", null), HttpStatus.UNAUTHORIZED);
        }
        HashMap<String, Chat> chats = userRepository.getChatHistory(fromUsername);
        return new ResponseEntity<>(new AllChatHistoryDTO("success", chats), HttpStatus.OK);
    }

    public ResponseEntity<LoginResponseDTO> login(UserRequestDTO userRequestDTO) {
        String username = userRequestDTO.getUsername();
        String password = userRequestDTO.getPassword();
        LoginResponseDTO loginResponseDTO;
        if(userRepository.userExists(username)){
            String token = generateToken(username);
            User user = userRepository.findByUsername(username).get();
            user.setToken(token);
            user.setValidTill(new Timestamp( System.currentTimeMillis()+(3600*24*10)));
            userRepository.save(user);
            loginResponseDTO = new LoginResponseDTO("success", token);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        }
        loginResponseDTO = new LoginResponseDTO("failure : Wrong User or Password!!!", null);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    private String generateToken(String username) {
        return username+"123";
    }

    private boolean isUserAuthenticated(String username, String apiKey) {
        if(userRepository.userExists(username)){
            User user = userRepository.findByUsername(username).get();
            if(Objects.nonNull(user.getToken()) && apiKey.equals(user.getToken()) && user.getValidTill().getTime() > System.currentTimeMillis()){
                return true;
            }
        }
        return false;
    }


    public ResponseEntity<Response> logout(UserRequestDTO userRequestDTO) {
        String username = userRequestDTO.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setToken(null);
            user.setValidTill(null);
            userRepository.save(user);
            return new ResponseEntity<>(new Response("success"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response("failure"), HttpStatus.NOT_FOUND);
    }
}
