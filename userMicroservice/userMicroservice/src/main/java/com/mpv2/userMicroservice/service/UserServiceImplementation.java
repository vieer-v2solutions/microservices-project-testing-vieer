package com.mpv2.userMicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mpv2.userMicroservice.entity.Ratings;
import com.mpv2.userMicroservice.entity.User;
import com.mpv2.userMicroservice.exception.ResourceNotFoundException;
import com.mpv2.userMicroservice.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

    public Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    private RestTemplate restTemplate;

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
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not found on server with id: " + userId));

        ArrayList<Ratings> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId() , ArrayList.class);
        logger.info("{}", ratingsOfUser);

        user.setRatings(ratingsOfUser);
        return user;
    }
}

//orElseThrow(()-> new ResourceNotFoundException("User Not found on server with id: " + userId)