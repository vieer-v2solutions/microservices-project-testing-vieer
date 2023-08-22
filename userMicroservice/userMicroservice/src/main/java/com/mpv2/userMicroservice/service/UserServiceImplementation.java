package com.mpv2.userMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpv2.userMicroservice.entity.User;
import com.mpv2.userMicroservice.exception.ResourceNotFoundException;
import com.mpv2.userMicroservice.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not found on server with id: " + userId));
    }
}

//orElseThrow(()-> new ResourceNotFoundException("User Not found on server with id: " + userId)