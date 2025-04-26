package com.projectmanagement.dto;

import java.math.BigDecimal;
import java.util.List;

public class JobSelectionDTO {

    private String email;
    private List<String> interviewsCleared;
    private String jobName;
    private String location;
    private BigDecimal payRate;
    private Boolean backgroundCleared;
    private String backgroundMessage;

    // → getters & setters …

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getInterviewsCleared() {
        return interviewsCleared;
    }
    public void setInterviewsCleared(List<String> interviewsCleared) {
        this.interviewsCleared = interviewsCleared;
    }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BigDecimal getPayRate() { return payRate; }
    public void setPayRate(BigDecimal payRate) { this.payRate = payRate; }

    public Boolean getBackgroundCleared() { return backgroundCleared; }
    public void setBackgroundCleared(Boolean backgroundCleared) {
        this.backgroundCleared = backgroundCleared;
    }

    public String getBackgroundMessage() { return backgroundMessage; }
    public void setBackgroundMessage(String backgroundMessage) {
        this.backgroundMessage = backgroundMessage;
    }
}
