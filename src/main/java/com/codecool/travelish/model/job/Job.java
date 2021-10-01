package com.codecool.travelish.model.job;

import com.codecool.travelish.model.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private LocalDate date;

    private String country;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int salary;

    private String city;


    @ManyToOne
    private Company company;

    public Job(String title, LocalDate date, String country, JobType  jobType, ExperienceType experienceType, String category, String description, int salary, String city, Company company) {
        this.title = title;
        this.date = date;
        this.country = country;
        this.jobType = jobType;
        this.experienceType = experienceType;
        this.category = category;
        this.description = description;
        this.salary = salary;
        this.city = city;
        this.company = company;
    }

}
