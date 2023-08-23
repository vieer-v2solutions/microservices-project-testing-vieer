package com.mpv2.hotelMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpv2.hotelMicroservice.entities.Hotel;
import com.mpv2.hotelMicroservice.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/addhotel")
    public Hotel saveHotel(@RequestBody Hotel hotel){
        return hotelService.saveHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotel();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable("id") String hotelId){
        return hotelService.getHotelById(hotelId);
    }
}
