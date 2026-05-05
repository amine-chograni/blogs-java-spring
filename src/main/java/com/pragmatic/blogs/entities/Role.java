package com.pragmatic.blogs.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "ROLE_USER", "ROLE_ADMIN"

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
