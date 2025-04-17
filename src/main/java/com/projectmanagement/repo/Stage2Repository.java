package com.projectmanagement.repo;

import com.projectmanagement.model.Stage2Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Stage2Repository extends JpaRepository<Stage2Details, Long> {
    Stage2Details findByUserEmail(String email);
}
