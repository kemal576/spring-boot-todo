package com.sahinkemal.todoapp.Entity;

public class LoginResponse {
    private Long userId;
    private String jwt;

    public LoginResponse(Long userId, String jwt) {
        this.userId = userId;
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
