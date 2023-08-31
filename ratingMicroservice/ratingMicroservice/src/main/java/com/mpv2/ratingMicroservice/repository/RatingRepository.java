package com.mpv2.ratingMicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpv2.ratingMicroservice.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {
    
    public List<Rating> findByUserId(String userId);

    public List<Rating> findByHotelId(String hotelId);


}
