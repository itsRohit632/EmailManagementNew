package com.projectmanagement.dto;

import java.time.LocalDate;

public class Stage3MockDTO {
    private String email;
    private String testName;
    private Integer score;
    private LocalDate testDate;
    private String status;

    public String getEmail() {
        return email;
    }

    public String getTestName() {
        return testName;
    }

    public Integer getScore() {
        return score;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public String getStatus() {
        return status;
    }
}
