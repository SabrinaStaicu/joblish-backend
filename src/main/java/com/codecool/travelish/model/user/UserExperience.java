package com.codecool.travelish.model.user;

public class UserExperience {
    private Long id;
    private String name;
    private String position;

    public UserExperience(Long id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public UserExperience() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
