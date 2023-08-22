package com.mpv2.userMicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="micro_users")
public class User {
    
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "E-Mail")
    private String email;
}
