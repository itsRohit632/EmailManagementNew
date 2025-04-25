package com.projectmanagement.service;

import com.projectmanagement.model.CourseMaterial;
import com.projectmanagement.model.Stage2Details;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.CourseMaterialRepository;
import com.projectmanagement.repo.Stage2Repository;
import com.projectmanagement.repo.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseMaterialService {

    private final CourseMaterialRepository courseMaterialRepository;
    private final Stage2Repository stage2Repository;
    private final UserRepository userRepository;

    public CourseMaterialService(CourseMaterialRepository courseMaterialRepository,
                                 Stage2Repository stage2Repository,
                                 UserRepository userRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
        this.stage2Repository = stage2Repository;
        this.userRepository = userRepository;
    }

    public String uploadMaterial(String courseName, String title, MultipartFile file) throws IOException {
        String uploadDir = System.getProperty("user.dir") + "/uploads/materials/" + courseName + "/";
        File directory = new File(uploadDir);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Could not create upload directory: " + uploadDir);
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);
        file.transferTo(destination);

        CourseMaterial material = new CourseMaterial();
        material.setCourseName(courseName);
        material.setTitle(title);
        material.setFilePath(uploadDir + fileName);
        material.setUploadTime(LocalDateTime.now());

        courseMaterialRepository.save(material);
        return "Material uploaded successfully!";
    }

    public List<CourseMaterial> getMaterialsForUser(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + email);
        }

        User user = userOpt.get();
        Stage2Details stage2 = stage2Repository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User is not enrolled in any course."));

        return courseMaterialRepository.findByCourseName(stage2.getCourseName());
    }
}
