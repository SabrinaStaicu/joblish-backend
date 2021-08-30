package com.codecool.travelish.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String intro;
    private int phone;
    @OneToOne
    private Resume resume;
    private String avatar;
    private String email;
//    private String password;


    public User(String firstName, String lastName, String country, String city, String intro, int phone, Resume resume, String avatar, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.intro = intro;
        this.phone = phone;
        this.resume = resume;
        this.avatar = avatar;
        this.email = email;
    }

}
