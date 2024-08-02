package com.example.job_portal.usermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(min = 6, message = "USERNAME_INVALID")
    private String username;
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    @ManyToMany
    Set<Role> roles;
}
