package com.projectmanagement.repo;

import com.projectmanagement.model.Stage3MockTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Stage3Repository extends JpaRepository<Stage3MockTest, Long> {
    List<Stage3MockTest> findByUserEmail(String email);
}
