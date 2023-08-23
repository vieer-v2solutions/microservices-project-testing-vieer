package com.mpv2.ratingMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpv2.ratingMicroservice.entity.Rating;
import com.mpv2.ratingMicroservice.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    @Autowired
    RatingService ratingService;

    //create controller
    @PostMapping("/addrating")
    public Rating saveRating(@RequestBody Rating rating){
        return ratingService.saveRating(rating);
    }

    //Get all ratings
    @GetMapping
    public List<Rating> getAllRatings(){
        return ratingService.getAllRatings();
    }

    //Get rating by User id
    @GetMapping("/users/{userId}")
    public List<Rating> getRatingByUserId(@PathVariable String userId){
        return ratingService.getRatingByUserId(userId);
    }

    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getRatingByHotelId(@PathVariable String hotelId){
        return ratingService.getRatingByHotelId(hotelId);
    }
}
