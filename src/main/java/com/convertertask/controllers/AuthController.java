package com.convertertask.controllers;

import com.convertertask.models.UserDetailsImpl;
import com.convertertask.models.UserModel;
import com.convertertask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView getRegister(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetailsImpl> signup(@RequestBody UserDetailsImpl user){
        if (userService.saveUser(user)){
            return new ResponseEntity<UserDetailsImpl>(user,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<UserDetailsImpl>(HttpStatus.FORBIDDEN);
        }
    }

}
