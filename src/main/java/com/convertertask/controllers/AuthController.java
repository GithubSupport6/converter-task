package com.convertertask.controllers;

import com.convertertask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public void signup(@ModelAttribute("userForm") User user){


    }

}
