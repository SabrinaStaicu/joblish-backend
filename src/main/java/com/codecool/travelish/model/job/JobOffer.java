package com.codecool.travelish.model.job;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class JobOffer {
    private Long id;
    private String name;
    private LocalDate date;
    private String country;
    private String jobType;
    private ExperienceType experienceType;
    private String company;
    private String companyImage;
    private String category;
    private String description;
    private int salary;
    private String city;


    public JobOffer(String name, LocalDate date, String country, String jobType, ExperienceType experienceType, String company, String companyImage, String category, String description, int salary, String city) {
        this.name = name;
        this.date = date;
        this.country = country;
        this.jobType = jobType;
        this.experienceType = experienceType;
        this.company = company;
        this.companyImage = companyImage;
        this.category = category;
        this.description = description;
        this.salary = salary;
        this.city = city;
    }

    public JobOffer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
