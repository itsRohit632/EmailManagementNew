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

    // ✅ Admin uploads a course material
    public String uploadMaterial(String courseName, String title, MultipartFile file) throws IOException {
        // Define the directory (inside the running project directory)
        String uploadDir = System.getProperty("user.dir") + "/uploads/materials/";

        // Ensure the directory exists
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IOException("Could not create upload directory: " + uploadDir);
            }
        }

        // Generate a unique filename
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);

        // Save file to the filesystem
        file.transferTo(destination);

        // Save metadata to the database
        CourseMaterial material = new CourseMaterial();
        material.setCourseName(courseName);
        material.setTitle(title);
        material.setFilePath(uploadDir + fileName); // optional: can be a relative path

        courseMaterialRepository.save(material);
        return "Material uploaded successfully!";
    }

    // ✅ Student retrieves materials based on enrolled course
    public List<CourseMaterial> getMaterialsForUser(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + email);
        }

        Stage2Details stage2 = stage2Repository.findByUser(userOpt.get())
                .orElseThrow(() -> new RuntimeException("User is not enrolled in any course."));

        String courseName = stage2.getCourseName();
        return courseMaterialRepository.findByCourseName(courseName);
    }
}
