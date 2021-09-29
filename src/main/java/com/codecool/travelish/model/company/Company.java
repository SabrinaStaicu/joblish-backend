package com.codecool.travelish.model.company;

import com.codecool.travelish.model.user.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

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

    @Column(columnDefinition = "TEXT")
    private String logo;

    private String description;

    @NotBlank
    private String category;

    @ElementCollection
    private Set<UserRole> roles = Set.of(UserRole.ROLE_COMPANY);

    public Company(String name, String email, String password, String logo, String description, String category) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.logo = logo;
        this.description = description;
        this.category = category;
    }
}