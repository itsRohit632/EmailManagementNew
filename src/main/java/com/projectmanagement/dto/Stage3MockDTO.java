package com.projectmanagement.dto;

import com.projectmanagement.model.MockScore;
import java.util.List;

public class Stage3MockDTO {
    private List<MockScore> scores;
    private double average;

    public Stage3MockDTO() {}

    public Stage3MockDTO(List<MockScore> scores, double average) {
        this.scores = scores;
        this.average = average;
    }

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
}
