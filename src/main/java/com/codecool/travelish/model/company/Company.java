package com.codecool.travelish.model.company;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Size(max = 30)
    private String name;
    @NotBlank
    @Size(max = 30)
    @Email
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;
    private String logo;
    private String description;
    @NotBlank
    private String category;

    public Company(String name, String email, String password, String logo, String description, String category) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.logo = logo;
        this.description = description;
        this.category = category;
    }
}