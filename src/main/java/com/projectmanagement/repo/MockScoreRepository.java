package com.projectmanagement.repo;

import com.projectmanagement.model.MockScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MockScoreRepository extends JpaRepository<MockScore, Long> {
    List<MockScore> findByEmail(String email);
}
