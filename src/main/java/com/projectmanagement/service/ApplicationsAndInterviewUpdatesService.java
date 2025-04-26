// src/main/java/com/projectmanagement/service/ApplicationsAndInterviewUpdatesService.java
package com.projectmanagement.service;

import com.projectmanagement.dto.ApplicationsAndInterviewUpdatesDTO;
import com.projectmanagement.model.ApplicationsAndInterviewUpdates;
import com.projectmanagement.repo.ApplicationsAndInterviewUpdatesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApplicationsAndInterviewUpdatesService {

    private final ApplicationsAndInterviewUpdatesRepository repo;

    public ApplicationsAndInterviewUpdatesService(
            ApplicationsAndInterviewUpdatesRepository repo) {
        this.repo = repo;
    }

    /**
     * Create or update the record for this email,
     * enforcing rtrCount >= submissions.
     */
    public String submitUpdates(ApplicationsAndInterviewUpdatesDTO dto) {
        if (dto.getRtrCount() < dto.getSubmissions()) {
            throw new IllegalArgumentException(
                "RTR count (" + dto.getRtrCount() + 
                ") must be greater than or equal to submissions (" + dto.getSubmissions() + ")."
            );
        }

        ApplicationsAndInterviewUpdates entity = repo
            .findByEmail(dto.getEmail())
            .orElseGet(ApplicationsAndInterviewUpdates::new);

        entity.setEmail(dto.getEmail());
        entity.setMarketedBy(dto.getMarketedBy());
        entity.setNumberOfApplications(dto.getNumberOfApplications());
        entity.setRtrCount(dto.getRtrCount());
        entity.setSubmissions(dto.getSubmissions());
        entity.setInterviewsScheduled(dto.getInterviewsScheduled());
        entity.setInterviewsCleared(dto.getInterviewsCleared());
        entity.setInterviewsRejected(dto.getInterviewsRejected());
        entity.setPerformanceFeedback(dto.getPerformanceFeedback());
        entity.setLastUpdated(LocalDateTime.now());

        repo.save(entity);
        return "Applications & interview updates saved successfully.";
    }

    public ApplicationsAndInterviewUpdates getUpdates(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
