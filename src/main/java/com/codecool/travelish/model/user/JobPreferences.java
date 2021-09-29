package com.codecool.travelish.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class JobPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean openToWork;

    @ElementCollection
    private List<String> jobTitles = new ArrayList<>();

    @ElementCollection
    private List<String> locations = new ArrayList<>();

    @ElementCollection
    private List<String> jobTypes =  new ArrayList<>();

    public JobPreferences(boolean openToWork, List<String> jobTitles, List<String> locations, List<String> jobTypes) {
        this.openToWork = openToWork;
        this.jobTitles = jobTitles;
        this.locations = locations;
        this.jobTypes = jobTypes;
    }

    public JobPreferences(boolean openToWork) {
        this.openToWork = openToWork;
    }


}
