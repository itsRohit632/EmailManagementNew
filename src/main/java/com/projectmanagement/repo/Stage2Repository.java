package com.projectmanagement.repo;

import com.projectmanagement.model.Stage2Details;
import com.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Stage2Repository extends JpaRepository<Stage2Details, Long> {
    Optional<Stage2Details> findByUser(User user); 
    Optional<Stage2Details> findByUserEmail(String email);
}
