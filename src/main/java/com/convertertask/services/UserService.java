package com.convertertask.services;

import com.convertertask.entities.User;
import com.convertertask.models.UserDetailsImpl;
import com.convertertask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()){
            return new UserDetailsImpl(user.get());
        }
        else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public boolean saveUser(UserDetailsImpl user){
        Optional<User> presented = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (presented.isPresent()){
            return false;
        }
        else {
            userRepository.save(new User(user));
            return true;
        }
    }
}
