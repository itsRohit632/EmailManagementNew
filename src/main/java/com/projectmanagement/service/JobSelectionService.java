package com.projectmanagement.service;

import com.projectmanagement.dto.JobSelectionDTO;
import com.projectmanagement.model.JobSelection;
import com.projectmanagement.repo.JobSelectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobSelectionService {

    private final JobSelectionRepository repo;

    public JobSelectionService(JobSelectionRepository repo) {
        this.repo = repo;
    }

    /**
     * Create or update the job‐selection record for a candidate.
     */
    public String submitSelection(JobSelectionDTO dto) {
        JobSelection entity = repo
            .findByEmail(dto.getEmail())
            .orElseGet(JobSelection::new);

        entity.setEmail(dto.getEmail());
        entity.setInterviewsCleared(dto.getInterviewsCleared());
        entity.setJobName(dto.getJobName());
        entity.setLocation(dto.getLocation());
        entity.setPayRate(dto.getPayRate());
        entity.setBackgroundCleared(dto.getBackgroundCleared());
        // If not cleared, ensure a failure message is provided
        if (!dto.getBackgroundCleared() && (dto.getBackgroundMessage() == null || dto.getBackgroundMessage().isBlank())) {
            throw new IllegalArgumentException("Background check failed: please provide a failure reason.");
        }
        entity.setBackgroundMessage(dto.getBackgroundMessage());
        entity.setLastUpdated(LocalDateTime.now());

        repo.save(entity);
        return "Job selection saved successfully.";
    }

    /**
     * Retrieve the job‐selection by candidate email.
     */
    public JobSelection getSelection(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
