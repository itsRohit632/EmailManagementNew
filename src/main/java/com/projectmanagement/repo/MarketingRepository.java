package com.projectmanagement.repo;

import com.projectmanagement.model.Marketing;
import com.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketingRepository extends JpaRepository<Marketing, Long> {
    Optional<Marketing> findByUser(User user);
}
