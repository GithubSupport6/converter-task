package com.convertertask.models;

import com.convertertask.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class UserDetailsImpl implements UserDetails {

    String username;
    String password;
    List<GrantedAuthority> authorities = new LinkedList<>();
    boolean isEnabled = true;

    public UserDetailsImpl(){}

    public UserDetailsImpl(String username,String password){
        this.username = username;
        this.password = password;
        isEnabled = true;
    }

    public UserDetailsImpl(User user){
        username = user.getUsername();
        password = user.getPassword();
        isEnabled = user.isEnabled();
        authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
