package com.projectmanagement.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // ✅ Ensures no duplicate role names in DB
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public RoleName getName() { return name; }

    public void setName(RoleName name) { this.name = name; }
}

