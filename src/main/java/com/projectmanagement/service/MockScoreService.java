package com.projectmanagement.service;

import com.projectmanagement.dto.MockScoreSummary;
import com.projectmanagement.model.MockScore;
import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.MockScoreRepository;
import com.projectmanagement.repo.ResumeScreeningRepository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MockScoreService {

    private final MockScoreRepository mockScoreRepo;
    private final UserRepository userRepository;
    private final ResumeScreeningRepository screeningRepository;

    public MockScoreService(MockScoreRepository mockScoreRepo, UserRepository userRepository,
                            ResumeScreeningRepository screeningRepository) {
        this.mockScoreRepo = mockScoreRepo;
        this.userRepository = userRepository;
        this.screeningRepository = screeningRepository;
    }

    // ✅ Save score and trigger evaluation
    public String saveScore(MockScore score) {
        mockScoreRepo.save(score);
        autoTransitionToResumeScreening(score.getEmail());
        return "Mock score submitted successfully.";
    }

    // ✅ Automatically move to Resume Screening if eligible
    public void autoTransitionToResumeScreening(String email) {
        List<MockScore> scores = mockScoreRepo.findByEmail(email);
        if (scores.isEmpty()) return;

        double avg = scores.stream().mapToInt(MockScore::getScore).average().orElse(0.0);
        if (avg < 60) return;

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return;

        User user = userOpt.get();

        // Prevent duplicate screening entry
        if (screeningRepository.findByUser(user).isPresent()) return;

        ResumeScreening screening = new ResumeScreening();
        screening.setUser(user);
        screening.setTechnology("AutoAssigned");
        screening.setScreenedBy("System");
        screening.setFeedback("Eligible for screening. Evaluated on communication, fluency, tech knowledge, and confidence.");
      //*****************************//
        screening.setIsPassed(true);
      //*****************************//
        //screening.setIsPassed(true);

        // Auto-generate scores (you can modify this logic later if needed)
        screening.setCommunication(7);
        screening.setFluency(8);
        screening.setTechnicalKnowledge(7);
        screening.setConfidence(8);

        screeningRepository.save(screening);
    }

    // ✅ Fetch all scores by email
    public List<MockScore> getAllScores(String email) {
        return mockScoreRepo.findByEmail(email);
    }

    // ✅ Fetch average score
    public double getAverageScore(String email) {
        List<MockScore> scores = mockScoreRepo.findByEmail(email);
        return scores.stream().mapToInt(MockScore::getScore).average().orElse(0.0);
    }

    // ✅ Fetch score list + average as summary
    public MockScoreSummary getScoreSummary(String email) {
        List<MockScore> scores = mockScoreRepo.findByEmail(email);
        double average = scores.stream().mapToInt(MockScore::getScore).average().orElse(0.0);
        return new MockScoreSummary(scores, average);
    }
}
