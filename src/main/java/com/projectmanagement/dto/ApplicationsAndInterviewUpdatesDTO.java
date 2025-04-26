package com.projectmanagement.dto;

public class ApplicationsAndInterviewUpdatesDTO {

    private String email;
    private String marketedBy;
    private int numberOfApplications;
    private int rtrCount;
    private int submissions;
    private int interviewsScheduled;
    private int interviewsCleared;
    private int interviewsRejected;
    private String performanceFeedback;

    // Getters & Setters

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMarketedBy() { return marketedBy; }
    public void setMarketedBy(String marketedBy) { this.marketedBy = marketedBy; }

    public int getNumberOfApplications() { return numberOfApplications; }
    public void setNumberOfApplications(int numberOfApplications) { this.numberOfApplications = numberOfApplications; }

    public int getRtrCount() { return rtrCount; }
    public void setRtrCount(int rtrCount) { this.rtrCount = rtrCount; }

    public int getSubmissions() { return submissions; }
    public void setSubmissions(int submissions) { this.submissions = submissions; }

    public int getInterviewsScheduled() { return interviewsScheduled; }
    public void setInterviewsScheduled(int interviewsScheduled) { this.interviewsScheduled = interviewsScheduled; }

    public int getInterviewsCleared() { return interviewsCleared; }
    public void setInterviewsCleared(int interviewsCleared) { this.interviewsCleared = interviewsCleared; }

    public int getInterviewsRejected() { return interviewsRejected; }
    public void setInterviewsRejected(int interviewsRejected) { this.interviewsRejected = interviewsRejected; }

    public String getPerformanceFeedback() { return performanceFeedback; }
    public void setPerformanceFeedback(String performanceFeedback) { this.performanceFeedback = performanceFeedback; }
}
