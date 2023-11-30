package com.mpv2.userMicroservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mpv2.userMicroservice.entity.Hotel;
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

    // Get user by Id, also retrieves the ratings and hotel name
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found on server with id: " + userId));

        // Retrieving the user's hotel ratings
        Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                Ratings[].class);

        logger.info("Rating Module called");
        logger.info("Ratings of user fetched: " + Arrays.toString(ratingsOfUser));

        List<Ratings> ratings = Arrays.stream(ratingsOfUser).toList();

        // Retrieving the hotel details
        List<Ratings> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity = restTemplate
                    .getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Hotel Module called");
            logger.info("Hotel details: " + hotel);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}

// orElseThrow(()-> new ResourceNotFoundException("User Not found on server with
// id: " + userId)