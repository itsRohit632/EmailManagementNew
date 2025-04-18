package com.projectmanagement.dto;

import com.projectmanagement.model.MockScore;
import java.util.List;

public class MockScoreSummary {

    private List<MockScore> scores;
    private double average;

    // No-arg constructor
    public MockScoreSummary() {
    }

    // All-arg constructor
    public MockScoreSummary(List<MockScore> scores, double average) {
        this.scores = scores;
        this.average = average;
    }

    // Getters and Setters
    public List<MockScore> getScores() {
        return scores;
    }

    public void setScores(List<MockScore> scores) {
        this.scores = scores;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "MockScoreSummary{" +
                "scores=" + scores +
                ", average=" + average +
                '}';
    }
}
