package com.mpv2.hotelMicroservice.service;

import java.util.List;

import com.mpv2.hotelMicroservice.entities.Hotel;

public interface HotelService {
    
    //Get all Hotel
    public List<Hotel> getAllHotel();

    //Get by id
    public Hotel getHotelById(String hotelId);

    //Create a user
    public Hotel saveHotel(Hotel hotel);
}
