package com.northeastern.edu.models;

public enum Role {

    RESIDENT("resident"),
    CRITIC("critic"),
    ADMIN("admin"),
    CENSOR("censor");

    private String role;

    public String getRole() {
        return this.role;
    }

    Role(String role) {
        this.role = role;
    }
}
