package com.convertertask.entities;

import com.convertertask.models.UserDetailsImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    String username;

    String password;

    String roles;

    boolean isEnabled;

    public User() {}

    public User(String login, String password){
        this.password = password;
        this.username = login;
        isEnabled = true;
    }

    public User(UserDetailsImpl user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getAuthorities().stream().map(Objects::toString).collect(Collectors.joining(", "));
        this.isEnabled = user.isEnabled();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
