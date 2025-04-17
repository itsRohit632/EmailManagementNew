package com.projectmanagement.dto;

import java.time.LocalDate;

public class Stage2DTO {
    private String email;
    private String courseName;
    private String trainingStatus;
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private String assignedBy;

    public String getEmail() {
        return email;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }
}
