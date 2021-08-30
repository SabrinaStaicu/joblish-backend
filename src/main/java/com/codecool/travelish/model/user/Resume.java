package com.codecool.travelish.model.user;

import java.util.List;

public class Resume {
    private Long id;
    private List<UserExperience> experience;
    private List<UserEducation> education;

    public Resume(Long id, List<UserExperience> experience, List<UserEducation> education) {
        this.id = id;
        this.experience = experience;
        this.education = education;
    }

    public Resume() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserExperience> getExperience() {
        return experience;
    }

    public void setExperience(List<UserExperience> experience) {
        this.experience = experience;
    }

    public List<UserEducation> getEducation() {
        return education;
    }

    public void setEducation(List<UserEducation> education) {
        this.education = education;
    }
}
