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
    @OneToMany(targetEntity=UserExperience.class,cascade = CascadeType.ALL)
    private List<UserExperience> experience;
    @OneToMany(targetEntity = UserEducation.class, cascade=CascadeType.ALL)
    private List<UserEducation> education;

    public Resume(List<UserExperience> experience, List<UserEducation> education) {
        this.experience = experience;
        this.education = education;
    }
}
