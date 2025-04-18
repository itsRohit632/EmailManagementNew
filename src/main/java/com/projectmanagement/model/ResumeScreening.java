package com.projectmanagement.model;

import jakarta.persistence.*;

@Entity
public class ResumeScreening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String technology;
    private String screenedBy;
    private boolean isPassed;
    private String feedback;

    // ---------- Getters and Setters ----------

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

    public String getScreenedBy() {
        return screenedBy;
    }
    public void setScreenedBy(String screenedBy) {
        this.screenedBy = screenedBy;
    }

    public boolean isPassed() {
        return isPassed;
    }
    public void setPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
