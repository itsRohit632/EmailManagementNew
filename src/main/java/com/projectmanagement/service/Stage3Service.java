package com.projectmanagement.service;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.MockScore;
import com.projectmanagement.repo.MockScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Stage3Service {

    private final MockScoreRepository mockScoreRepository;

    public Stage3Service(MockScoreRepository mockScoreRepository) {
        this.mockScoreRepository = mockScoreRepository;
    }

    public String addMockScore(MockScore score) {
        mockScoreRepository.save(score);
        return "Mock score added successfully!";
    }

    public List<MockScore> getMockScores(String email) {
        return mockScoreRepository.findByEmail(email);
    }

    public Stage3MockDTO getMockSummary(String email) {
        List<MockScore> scores = mockScoreRepository.findByEmail(email);
        double average = scores.stream().mapToInt(MockScore::getScore).average().orElse(0.0);
        return new Stage3MockDTO(scores, average);
    }
}
