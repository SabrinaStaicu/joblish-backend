package com.codecool.travelish.model.job;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class JobOffer {
    private Long id;
    private String name;
    private LocalDate date;
    private JobLocation location;
    private String jobType;
    private ExperienceType experienceType;
    private String company;
    private String companyImage;
    private String category;
    private String description;
    private int salary;

    public JobOffer(String name, LocalDate date, JobLocation location, String jobType, ExperienceType experienceType, String company, String companyImage, String category, String description, int salary) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.jobType = jobType;
        this.experienceType = experienceType;
        this.company = company;
        this.companyImage = companyImage;
        this.category = category;
        this.description = description;
        this.salary = salary;
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

    @OneToOne
    public JobLocation getLocation() {
        return location;
    }

    public void setLocation(JobLocation location) {
        this.location = location;
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

    @Id
    public Long getId() {
        return id;
    }
}
