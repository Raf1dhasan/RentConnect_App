package com.rafid.rect_connect;

public class HelperClass {
    private String name, email, username;

    // Constructor
    public HelperClass(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    // Default constructor (required for Firebase)
    public HelperClass() {
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
