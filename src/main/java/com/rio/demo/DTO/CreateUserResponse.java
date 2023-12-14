package com.rio.demo.DTO;

public class CreateUserResponse extends Response{
    private String message;

    public CreateUserResponse(String status, String message) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
