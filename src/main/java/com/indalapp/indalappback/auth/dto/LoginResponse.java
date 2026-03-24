package com.indalapp.indalappback.auth.dto;

public class LoginResponse {

    private Long id;
    private String username;
    private String role;
    private boolean active;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(Long id, String username, String role, boolean active, String message) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.active = active;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}