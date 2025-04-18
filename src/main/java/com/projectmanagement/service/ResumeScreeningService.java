package com.projectmanagement.service;

import com.projectmanagement.dto.ResumeScreeningDTO;
import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.ResumeScreeningRepository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeScreeningService {

    private final ResumeScreeningRepository screeningRepo;
    private final UserRepository userRepo;

    public ResumeScreeningService(ResumeScreeningRepository screeningRepo, UserRepository userRepo) {
        this.screeningRepo = screeningRepo;
        this.userRepo = userRepo;
    }

    // ✅ Save resume screening result
    public String submitScreening(ResumeScreeningDTO dto) {
        Optional<User> userOpt = userRepo.findByEmail(dto.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found with email: " + dto.getEmail();
        }

        ResumeScreening screening = new ResumeScreening();
        screening.setUser(userOpt.get());
        screening.setTechnology(dto.getTechnology());
        screening.setScreenedBy(dto.getScreenedBy());
        screening.setPassed(dto.isPassed());
        screening.setFeedback(dto.getFeedback());

        screeningRepo.save(screening);
        return "Resume screening submitted successfully.";
    }

    // ✅ Check screening status
    public String checkScreening(String email) {
        ResumeScreening screening = screeningRepo.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("No screening record found for: " + email));

        return screening.isPassed()
                ? "✅ User PASSED resume screening."
                : "❌ User has NOT passed resume screening.";
    }
}
