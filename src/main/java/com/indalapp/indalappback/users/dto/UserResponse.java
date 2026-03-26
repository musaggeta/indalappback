package com.indalapp.indalappback.users.dto;

public class UserResponse {

    private Long id;
    private String username;
    private String role;
    private boolean active;

    public UserResponse() {}

    public UserResponse(Long id, String username, String role, boolean active) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}