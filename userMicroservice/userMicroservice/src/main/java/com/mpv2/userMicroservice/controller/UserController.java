package com.mpv2.userMicroservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpv2.userMicroservice.entity.User;
import com.mpv2.userMicroservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    //Logger Statement
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //Create a user
    @PostMapping("/adduser")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    //Get all user
    @GetMapping
    public List<User> getUser(){
        LOGGER.info("Got all users");
        return userService.getAllUser();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String userId){
        LOGGER.info("User found at ID: " + userId);
        return userService.getUser(userId);
    }
}



