package com.codecool.travelish.model.user;

import com.codecool.travelish.model.job.Job;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 25)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 25)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 25)
    private String city;

    private String experience;

    @NotBlank
    @Size(min = 3, max = 35)
    private String phone;

    private String picture;

    @ElementCollection
    private Set<UserRole> roles = Set.of(UserRole.ROLE_USER);

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    private Boolean lookingForJob;

    @Transient
    private LocalDate date;

    @Transient
    private String notes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Job> favoriteJobs;

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private JobPreferences jobPreferences;

    public AppUser(String firstName, String lastName, String city, String experience, String phone, String avatar, String email, String password, JobPreferences jobPreferences, List<String> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.experience = experience;
        this.phone = phone;
        this.picture = avatar;
        this.email = email;
        this.password = password;
        this.jobPreferences = jobPreferences;
        this.skills = skills;
    }

    public AppUser(String firstName, String lastName, String picture, LocalDate date, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.date = date;
        this.notes = notes;
    }

    public void addToFavorites(Job job) {
        favoriteJobs.add(job);
    }

    public void removeFromFavorites(Job job) {
        favoriteJobs.remove(job);
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

}
