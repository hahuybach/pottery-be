package com.example.pottery.db1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "[user]")
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "full_name")
    private String fullName;
    @Temporal(TemporalType.DATE)
    private LocalDate dob;
    private String phoneNumber;
    @OneToOne(mappedBy = "user",cascade = CascadeType.MERGE)
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;



}
