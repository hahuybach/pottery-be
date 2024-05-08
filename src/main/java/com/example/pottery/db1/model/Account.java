package com.example.pottery.db1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(nullable = false)
    @NotBlank(message = "password is required")
    private String password;
    @Email(message = "Invalid email address")
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "is_active")
    @NotNull(message = "is active is null")
    private Boolean isActive;

    @Column(name = "full_name")
    private String fullName;
    @Temporal(TemporalType.DATE)
    private LocalDate dob;
    private String phoneNumber;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private Set<AccountRole> accountRoles = new HashSet<>();
    @OneToMany(mappedBy = "account")
    private Set<Post> posts;
    @OneToMany(mappedBy = "account")
    private Set<Feedback> feedbacks;
}

