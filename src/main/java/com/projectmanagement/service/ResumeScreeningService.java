package com.projectmanagement.service;

import com.projectmanagement.dto.ResumeScreeningDTO;
import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.ResumeScreeningRepository;
import com.projectmanagement.repo.UserRepository;
import com.projectmanagement.utility.EmailService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeScreeningService {

    private final ResumeScreeningRepository repository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public ResumeScreeningService(ResumeScreeningRepository repository, UserRepository userRepository, EmailService emailService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public String submitScreening(ResumeScreeningDTO dto) {
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
        if (userOpt.isEmpty()) return "User not found!";

        User user = userOpt.get();

        if (repository.findByUser(user).isPresent()) {
            return "Resume screening already exists for this user.";
        }

        ResumeScreening screening = new ResumeScreening();
        screening.setUser(user);
        screening.setTechnology(dto.getTechnology());
        screening.setScreenedBy(dto.getScreenedBy());
        screening.setFeedback(dto.getFeedback());

        screening.setCommunication(dto.getCommunication());
        screening.setFluency(dto.getFluency());
        screening.setTechnicalKnowledge(dto.getTechnicalKnowledge());
        screening.setConfidence(dto.getConfidence());

        int total = dto.getCommunication() + dto.getFluency() + dto.getTechnicalKnowledge() + dto.getConfidence();
        double avg = total / 4.0;

        boolean passed = avg >= 6.0;
        
        //*****************************//
        screening.setIsPassed(passed);
      //*****************************//

        repository.save(screening);

        String result = passed ? "PASSED" : "FAILED";

        // Email is optional for now
        emailService.sendEmail(
                user.getEmail(),
                "Resume Screening Result",
                "Hi " + user.getFullName() + ",\n\nYour resume screening is complete.\nResult: " + result + "\nAverage Score: " + avg + "\nFeedback: " + dto.getFeedback()
        );

        return "Resume screening submitted successfully.";
    }

    public ResumeScreening getResult(String email) {
        return userRepository.findByEmail(email)
                .flatMap(repository::findByUser)
                .orElse(null);
    }
}
