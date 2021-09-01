package com.codecool.travelish.model.user;

import com.codecool.travelish.model.job.Job;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(max = 25)
    private String username;
    private String firstName;
    private String lastName;
    private String city;
    private String experience;
    private String phone;
    private String picture;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min = 5, max = 30)
    private String password;
    private Boolean lookingForJob;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Job> favoriteJobs;


    public AppUser(String firstName, String lastName, String city, String experience, String phone, String avatar, String email, String password, Boolean lookingForJob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.experience = experience;
        this.phone = phone;
        this.picture = avatar;
        this.email = email;
        this.password = password;
        this.lookingForJob = lookingForJob;
    }

    public void addToFavorites(Job job) {
        favoriteJobs.add(job);
    }

    public void removeFromFavorites(Job job) {
        favoriteJobs.remove(job);
    }

}
