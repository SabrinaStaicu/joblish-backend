package com.codecool.travelish.model.application;

import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "application")
@Data
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDate date;
    private String notes;
    @ManyToOne
    private Job job;

    @ManyToOne
    private AppUser appUser;

    public Application(ApplicationStatus status, Job job, AppUser appUser) {
        this.status = status;
        this.job = job;
        this.appUser = appUser;
    }
}
