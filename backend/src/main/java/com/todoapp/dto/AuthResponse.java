package com.todoapp.dto;

public class AuthResponse {

    private String token;
    private String email;
    private String message;

    public AuthResponse() {}

    public AuthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public AuthResponse(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
} 