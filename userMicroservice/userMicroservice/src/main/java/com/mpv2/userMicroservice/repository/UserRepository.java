package com.mpv2.userMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpv2.userMicroservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
