package com.mpv2.userMicroservice.service;


import java.util.List;

import com.mpv2.userMicroservice.entity.User;

public interface UserService {
    
    //Save User
    User saveUser(User user);

    //Get All User
    List<User> getAllUser();

    //Get User by userID
    User getUser(String userId);


}
