package com.projectmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Marketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String technology;

    private String marketedBy;

    private LocalDateTime marketedAt;

    private String status; // e.g., In Progress, Completed, etc.

    // ======== Getters and Setters ========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getMarketedBy() {
        return marketedBy;
    }

    public void setMarketedBy(String marketedBy) {
        this.marketedBy = marketedBy;
    }

    public LocalDateTime getMarketedAt() {
        return marketedAt;
    }

    public void setMarketedAt(LocalDateTime marketedAt) {
        this.marketedAt = marketedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
