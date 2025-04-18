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
    private String feedback;
    private Boolean isPassed;

    private Integer communication;
    private Integer fluency;
    private Integer technicalKnowledge;
    private Integer confidence;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTechnology() { return technology; }
    public void setTechnology(String technology) { this.technology = technology; }

    public String getScreenedBy() { return screenedBy; }
    public void setScreenedBy(String screenedBy) { this.screenedBy = screenedBy; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Boolean getIsPassed() { return isPassed; }
    public void setIsPassed(Boolean isPassed) { this.isPassed = isPassed; }

    public Integer getCommunication() { return communication; }
    public void setCommunication(Integer communication) { this.communication = communication; }

    public Integer getFluency() { return fluency; }
    public void setFluency(Integer fluency) { this.fluency = fluency; }

    public Integer getTechnicalKnowledge() { return technicalKnowledge; }
    public void setTechnicalKnowledge(Integer technicalKnowledge) { this.technicalKnowledge = technicalKnowledge; }

    public Integer getConfidence() { return confidence; }
    public void setConfidence(Integer confidence) { this.confidence = confidence; }
}
