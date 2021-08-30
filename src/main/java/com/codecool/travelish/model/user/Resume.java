package com.codecool.travelish.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<UserExperience> experience;
    @OneToMany
    private List<UserEducation> education;

    public Resume(List<UserExperience> experience, List<UserEducation> education) {
        this.experience = experience;
        this.education = education;
    }
}
