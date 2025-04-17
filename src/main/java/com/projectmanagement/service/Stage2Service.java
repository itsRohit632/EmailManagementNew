package com.projectmanagement.service;

import com.projectmanagement.dto.Stage2DTO;
import com.projectmanagement.model.Stage2Details;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.Stage2Repository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Stage2Service {

    private final Stage2Repository stage2Repository;
    private final UserRepository userRepository;

    public Stage2Service(Stage2Repository stage2Repository, UserRepository userRepository) {
        this.stage2Repository = stage2Repository;
        this.userRepository = userRepository;
    }

    public String assignCourse(Stage2DTO dto) {
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found with email: " + dto.getEmail();
        }

        Stage2Details details = new Stage2Details();
        details.setUser(userOpt.get());
        details.setCourseName(dto.getCourseName());
        details.setTrainingStatus(Stage2Details.TrainingStatus.valueOf(dto.getTrainingStatus()));
        details.setStartDate(dto.getStartDate());
        details.setExpectedEndDate(dto.getExpectedEndDate());
        details.setAssignedBy(dto.getAssignedBy());

        stage2Repository.save(details);
        return "Course assigned successfully to " + dto.getEmail();
    }

    public Stage2Details getStage2DetailsByEmail(String email) {
        return stage2Repository.findByUserEmail(email);
    }
}
