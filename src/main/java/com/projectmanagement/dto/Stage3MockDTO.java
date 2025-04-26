package com.projectmanagement.dto;

import com.projectmanagement.model.Stage3MockTest;
import java.util.List;

public class Stage3MockDTO {
    private List<Stage3MockTest> tests;
    private double average;

    public Stage3MockDTO() {
    }

    public Stage3MockDTO(List<Stage3MockTest> tests, double average) {
        this.tests = tests;
        this.average = average;
    }

    public List<Stage3MockTest> getTests() {
        return tests;
    }

    public void setTests(List<Stage3MockTest> tests) {
        this.tests = tests;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
