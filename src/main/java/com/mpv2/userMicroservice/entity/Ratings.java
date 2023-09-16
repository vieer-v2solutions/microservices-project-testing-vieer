package com.mpv2.userMicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ratings {
    private String rating;
    private String ratingId;
    private String feedback;
    private String userId;
    private String hotelId;

    private Hotel hotel;
}
