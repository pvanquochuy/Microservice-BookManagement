package com.example.job_portal.usermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String username;
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    @ManyToMany
    Set<Role> roles;
}
