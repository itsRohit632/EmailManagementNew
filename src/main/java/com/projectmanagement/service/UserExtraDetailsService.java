package com.projectmanagement.service;

import com.projectmanagement.model.User;
import com.projectmanagement.model.UserDetails;
import com.projectmanagement.repo.UserDetailsRepository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserExtraDetailsService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    public UserExtraDetailsService(UserDetailsRepository userDetailsRepository,
                                   UserRepository userRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository;
    }

    public String saveExtraDetails(
            String email,
            String referredBy,
            String firstName,
            String lastName,
            LocalDate dob,
            String eadType,
            LocalDate eadStartDate,
            Boolean hasPriorExperience,
            String priorExperienceDetails,
            String programmingLanguage,
            MultipartFile resumeFile,
            MultipartFile eadFile,
            MultipartFile idProofFile
    ) throws IOException {

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return "User with email '" + email + "' not found!";
        }

        // Ensure upload directory exists
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        // Save files and capture filenames
        String resumeFileName = saveFile(resumeFile, uploadPath);
        String eadFileName    = saveFile(eadFile, uploadPath);
        String idFileName     = saveFile(idProofFile, uploadPath);

        // Build and persist UserDetails
        UserDetails details = UserDetails.builder()
                .user(userOpt.get())
                .email(email)
                .referredBy(referredBy)
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .eadType(eadType)
                .eadStartDate(eadStartDate)
                .hasPriorExperience(hasPriorExperience)
                .priorExperienceDetails(priorExperienceDetails)
                .programmingLanguage(programmingLanguage)
                .resumeFileName(resumeFileName)
                .eadFileName(eadFileName)
                .idProofFileName(idFileName)
                .build();

        userDetailsRepository.save(details);
        return "User extra details saved successfully! One of our team members will reach out to you.";
    }

    /**
     * Save a single MultipartFile to disk, returning its stored filename.
     * @param file the uploaded file
     * @param uploadPath the path to the uploads directory
     * @return the generated filename, or null if no file was provided
     * @throws IOException if write fails
     */
    private String saveFile(MultipartFile file, Path uploadPath) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String originalName = file.getOriginalFilename();
        String filename = UUID.randomUUID() + "_" + (originalName != null ? originalName : "");
        Path target = uploadPath.resolve(filename);
        file.transferTo(target.toFile());
        return filename;
    }
}
