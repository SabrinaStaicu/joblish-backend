package com.codecool.travelish.model.application;

import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "application")
@Data
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    private Job job;

    @ManyToOne
    private AppUser appUser;


}
