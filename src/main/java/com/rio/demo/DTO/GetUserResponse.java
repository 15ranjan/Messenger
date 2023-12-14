package com.rio.demo.DTO;

import java.util.List;

public class GetUserResponse extends Response {
    private List<String> data;

    public GetUserResponse(String status, List<String> data) {
        super(status);
        this.data = data;
    }

    public List<String> getdata() {
        return data;
    }
}
