package com.rio.demo.DTO;

public class LoginResponseDTO extends Response{

    private String apiKey;

    public LoginResponseDTO(String status, String apiKey){
        super(status);
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

}
