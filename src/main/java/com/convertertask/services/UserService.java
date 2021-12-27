package com.convertertask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()){
            return user.get();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public boolean saveUser(User user){
        Optional<UserDetails> presented = Optional.ofNullable(loadUserByUsername(user.getUsername()));
        if (presented.isPresent()){
            return false;
        }
        else {
            userRepository.save(user);
            return true;
        }
    }
}
