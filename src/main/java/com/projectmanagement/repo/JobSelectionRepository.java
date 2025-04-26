package com.projectmanagement.repo;

import com.projectmanagement.model.JobSelection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSelectionRepository extends JpaRepository<JobSelection, Long> {
    Optional<JobSelection> findByEmail(String email);
}
