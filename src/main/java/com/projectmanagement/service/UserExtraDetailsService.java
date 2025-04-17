package com.projectmanagement.service;

import com.projectmanagement.model.User;
import com.projectmanagement.model.UserDetails;
import com.projectmanagement.repo.UserDetailsRepository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserExtraDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    public UserExtraDetailsService(UserDetailsRepository userDetailsRepository, UserRepository userRepository) {
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
            return "User with email \"" + email + "\" not found!";
        }

        String uploadDir = "uploads/";
        new File(uploadDir).mkdirs();

        String resumeFileName = saveFile(resumeFile, uploadDir);
        String eadFileName = saveFile(eadFile, uploadDir);
        String idFileName = saveFile(idProofFile, uploadDir);

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

    private String saveFile(MultipartFile file, String dir) throws IOException {
        if (file == null || file.isEmpty()) return null;
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(dir + filename);
        file.transferTo(destination);
        return filename;
    }
}
