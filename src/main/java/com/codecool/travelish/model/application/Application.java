package com.codecool.travelish.model.application;

import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Job job;

    @ManyToOne
    private AppUser appUser;

    public Application(ApplicationStatus status, Job job, AppUser appUser, LocalDate date) {
        this.status = status;
        this.job = job;
        this.appUser = appUser;
        this.date = date;
    }
}
