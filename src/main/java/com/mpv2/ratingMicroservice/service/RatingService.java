package com.mpv2.ratingMicroservice.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.mpv2.ratingMicroservice.entity.Rating;

public interface RatingService {
    
    //Create
    public Rating saveRating(@RequestBody Rating rating);

    //GetAllRatings
    public List<Rating> getAllRatings();

    //Get Ratings by UserId
    public List<Rating> getRatingByUserId(String userId);

    //Get Ratings by hotel
    public List<Rating> getRatingByHotelId(String hotelId);
}
