package com.projectmanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class JobSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;  // candidate’s email

    @ElementCollection
    @CollectionTable(name = "job_selection_interviews", joinColumns = @JoinColumn(name = "job_selection_id"))
    @Column(name = "interview_name")
    private List<String> interviewsCleared;

    private String jobName;
    private String location;
    private BigDecimal payRate;

    private boolean backgroundCleared;
    private String backgroundMessage;  // e.g. “Passed” or reason for failure

    private LocalDateTime lastUpdated;

    // → standard getters & setters …

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getInterviewsCleared() { return interviewsCleared; }
    public void setInterviewsCleared(List<String> interviewsCleared) {
        this.interviewsCleared = interviewsCleared;
    }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BigDecimal getPayRate() { return payRate; }
    public void setPayRate(BigDecimal payRate) { this.payRate = payRate; }

    public boolean isBackgroundCleared() { return backgroundCleared; }
    public void setBackgroundCleared(boolean backgroundCleared) {
        this.backgroundCleared = backgroundCleared;
    }

    public String getBackgroundMessage() { return backgroundMessage; }
    public void setBackgroundMessage(String backgroundMessage) {
        this.backgroundMessage = backgroundMessage;
    }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
