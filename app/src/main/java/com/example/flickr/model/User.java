package com.example.flickr.model;

import java.io.Serializable;

public class User implements Serializable {
    public String firstName;
    public String lastName;
    public String birthday;
    public String email;
    public String password;
    public String avatar;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String firstName, String lastName, String birthday, String email, String password, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }
}
