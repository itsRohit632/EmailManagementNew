package com.projectmanagement.service;

import com.projectmanagement.model.Marketing;
import com.projectmanagement.model.ResumeScreening;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.MarketingRepository;
import com.projectmanagement.repo.ResumeScreeningRepository;
import com.projectmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarketingService {

    private final MarketingRepository marketingRepository;
    private final ResumeScreeningRepository screeningRepository;
    private final UserRepository userRepository;

    public MarketingService(MarketingRepository marketingRepository, ResumeScreeningRepository screeningRepository, UserRepository userRepository) {
        this.marketingRepository = marketingRepository;
        this.screeningRepository = screeningRepository;
        this.userRepository = userRepository;
    }

    // ✅ Create marketing entry if screening passed
    public String processMarketing(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return "User not found!";

        User user = userOpt.get();

        Optional<ResumeScreening> screeningOpt = screeningRepository.findByUser(user);
        if (screeningOpt.isEmpty()) return "Resume screening not found!";
        
        ResumeScreening screening = screeningOpt.get();
        if (!screening.isPassed()) return "User has not passed screening.";

        if (marketingRepository.findByUser(user).isPresent()) {
            return "Marketing already exists for this user.";
        }

        Marketing marketing = new Marketing();
        marketing.setUser(user);
        marketing.setTechnology(screening.getTechnology());
        marketing.setMarketedBy("System");

        marketingRepository.save(marketing);
        return "Marketing record created successfully for: " + email;
    }

    // ✅ Get marketing info
    public Marketing getMarketing(String email) {
        return userRepository.findByEmail(email)
                .flatMap(marketingRepository::findByUser)
                .orElse(null);
    }
}
