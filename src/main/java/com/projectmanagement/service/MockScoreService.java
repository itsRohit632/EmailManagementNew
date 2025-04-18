package com.projectmanagement.service;

import com.projectmanagement.model.MockScore;
import com.projectmanagement.repo.MockScoreRepository;
import org.springframework.stereotype.Service;
import com.projectmanagement.dto.MockScoreSummary;

import java.util.List;

@Service
public class MockScoreService {

    private final MockScoreRepository repo;

    public MockScoreService(MockScoreRepository repo) {
        this.repo = repo;
    }

    // ✅ Save a mock score
    public String saveScore(MockScore score) {
        repo.save(score);
        return "Mock score submitted successfully.";
    }

    // 📋 Get all mock scores by email
    public List<MockScore> getAllScores(String email) {
        return repo.findByEmail(email);
    }

    // 📊 Evaluate average score for eligibility
    public String evaluate(String email) {
        List<MockScore> scores = repo.findByEmail(email);

        if (scores.isEmpty()) {
            return "No scores available for evaluation.";
        }

        double average = scores.stream()
                .mapToInt(MockScore::getScore) // ✅ No need for null check
                .average()
                .orElse(0.0);

        return average >= 60
                ? "✅ Eligible for Stage 4. Average score: " + average
                : "❌ Not eligible yet. Average score: " + average;
    }
    
    public MockScoreSummary getScoreSummary(String email) {
        List<MockScore> scores = repo.findByEmail(email);

        double average = scores.stream()
            .mapToInt(MockScore::getScore)
            .average()
            .orElse(0.0);

        return new MockScoreSummary(scores, average);
    }
}
