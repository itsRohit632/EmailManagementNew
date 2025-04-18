package com.projectmanagement.service;

import com.projectmanagement.dto.Stage3MockDTO;
import com.projectmanagement.model.Stage3MockTest;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.Stage3Repository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Stage3Service {

    private final Stage3Repository stage3Repository;
    private final UserRepository userRepository;

    public Stage3Service(Stage3Repository stage3Repository, UserRepository userRepository) {
        this.stage3Repository = stage3Repository;
        this.userRepository = userRepository;
    }

    public String addMockTest(Stage3MockDTO dto) {
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found with email: " + dto.getEmail();
        }

        Stage3MockTest mockTest = new Stage3MockTest();
        mockTest.setUser(userOpt.get());
        mockTest.setTestName(dto.getTestName());
        mockTest.setScore(dto.getScore());
        mockTest.setTestDate(dto.getTestDate());
        mockTest.setStatus(dto.getStatus());

        stage3Repository.save(mockTest);

        return "Mock test record added successfully!";
    }

    public List<Stage3MockTest> getMocksByEmail(String email) {
        return stage3Repository.findByUserEmail(email);
    }
}
