package com.rio.demo.DTO;

public class UserRequestDTO {
    private String username;
    private String password;

    public UserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
