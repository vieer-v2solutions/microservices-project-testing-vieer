package com.mpv2.hotelMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpv2.hotelMicroservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
    
}
