package com.mpv2.userMicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String star;
    
}
