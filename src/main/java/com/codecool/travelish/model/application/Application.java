package com.codecool.travelish.model.application;

import com.codecool.travelish.model.job.JobOffer;
import com.codecool.travelish.model.user.User;
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
    private JobOffer jobOffer;

    @ManyToOne
    private User user;


}
