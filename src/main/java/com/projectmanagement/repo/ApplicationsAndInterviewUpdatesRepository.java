package com.projectmanagement.repo;

import com.projectmanagement.model.ApplicationsAndInterviewUpdates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationsAndInterviewUpdatesRepository
        extends JpaRepository<ApplicationsAndInterviewUpdates, Long> {

    Optional<ApplicationsAndInterviewUpdates> findByEmail(String email);
}
