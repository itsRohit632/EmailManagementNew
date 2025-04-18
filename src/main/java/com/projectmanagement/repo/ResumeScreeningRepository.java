package com.projectmanagement.repo;

import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeScreeningRepository extends JpaRepository<ResumeScreening, Long> {
    Optional<ResumeScreening> findByUser(User user);
}
